/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

/**
 *
 * @author petr
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.LoadingStateChangeEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SetSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;

public class BottlesPresenter extends Presenter<BottlesPresenter.ViewDef, BottlesPresenter.Proxy> implements SelectionChangeEvent.Handler, AllBottlesStampedEvent.AllBottlesStampedHandler {

    public interface ViewDef extends View {
        AbstractButton getCreateButton();
        AbstractButton getEditButton();
        AbstractButton getStampButton();
        AbstractButton getDeleteButton();
        HasData<BottleDto> getBottlesTable();
        SetSelectionModel<BottleDto> getBottleTableSelection();
    }

    @ProxyStandard
    @NameToken(NameTokens.BOTTLES)
    public interface Proxy extends ProxyPlace<BottlesPresenter> {
    }
    
    private ListDataProvider<BottleDto> bottlesData;
    private final BottleService bottleService;
    private final ApplicationMessages messages;
    private final BottlePopupView bottlePopup;
    private final BottleStamper bottleStamper;
    private BottleDto editedItem;

    @Inject
    public BottlesPresenter(EventBus eventBus, ViewDef view, Proxy proxy, 
            BottleService bottleService, ApplicationMessages applicationMessages,
            BottlePopupView bottlePopupView, BottleStamper bottleStamper) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
        
        this.bottleService = bottleService;
        this.messages = applicationMessages;
        this.bottlePopup = bottlePopupView;
        this.bottleStamper = bottleStamper;
    }
    
    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getCreateButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                bottlePopup.setSubmitHandler(new BottlePopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(BottleDto bottle) {
                        bottle.setToxic(Math.random() > 0.5);
                        if (bottle.getProductionDate() == null)
                            bottle.setProductionDate(new Date());
                        createBottle(bottle);
                    }
                });
                bottlePopup.edit(new BottleDto());
                bottlePopup.show();
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                bottlePopup.setSubmitHandler(new BottlePopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(BottleDto bottle) {
                        updateBottle(bottle);
                    }
                });
                editedItem = getView().getBottleTableSelection().getSelectedSet().iterator().next();
                getView().getBottleTableSelection().clear();
                bottlePopup.edit(editedItem);
                bottlePopup.show();
            }
        }));

        registerHandler(getView().getStampButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Set<BottleDto> bottlesToStamp = getView().getBottleTableSelection().getSelectedSet();
                getView().getBottleTableSelection().clear();
                bottleStamper.stampBottles(bottlesToStamp);
            }
        }));

        registerHandler(getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Set<BottleDto> bottlesToDelete = getView().getBottleTableSelection().getSelectedSet();
                bottlesData.getList().removeAll(bottlesToDelete);
                getView().getBottleTableSelection().clear();
                for (BottleDto bottle : bottlesToDelete) {
                    deleteBottle(bottle);
                }
                bottlesData.flush();
            }
        }));

        registerHandler(getView().getBottleTableSelection().addSelectionChangeHandler(this));
        registerHandler(getEventBus().addHandler(AllBottlesStampedEvent.getType(), this));

        bottlesData = new ListDataProvider<>();
        bottlesData.addDataDisplay(getView().getBottlesTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event) {
        Set<BottleDto> selectedItems = getView().getBottleTableSelection().getSelectedSet();
        getView().getEditButton().setEnabled(selectedItems.size() == 1);
        getView().getDeleteButton().setEnabled(selectedItems.size() > 0);
        getView().getStampButton().setEnabled(getNumberOfSelectedNotStampedNotToxicBottles(selectedItems) > 0);
    }

    private int getNumberOfSelectedNotStampedNotToxicBottles(Set<BottleDto> selectedBottles){
        int notStampedBottles = 0;
        for (BottleDto bottleDto : selectedBottles){
            if (bottleDto.getStampDate() == null && !bottleDto.isToxic())
                notStampedBottles += 1;
        }
        return notStampedBottles;
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        bottleService.getBottles(new MethodCallback<List<BottleDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(messages.loadBottleError(exception.getLocalizedMessage()));
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }

            @Override
            public void onSuccess(Method method, List<BottleDto> response) {
                bottlesData.setList(response);
                bottlesData.flush();
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }
        });
    }

    private void createBottle(final BottleDto bottle) {
        bottleService.createBottle(bottle, new MethodCallback<BottleDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                NotificationUtils.error(messages.createBottleError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, BottleDto createdBottle) {
                fetchData();
                NotificationUtils.info(messages.bottleCreated(createdBottle.getName()));
            }
        });
    }

    private void updateBottle(final BottleDto bottle) {
        bottleService.updateBottle(bottle.getId(), bottle, new MethodCallback<BottleDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                NotificationUtils.error(messages.updateBottleError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, BottleDto bottleDto) {
                fetchData();
                editedItem = null;
                NotificationUtils.info(messages.bottleUpdated(bottleDto.getName()));
            }
        });
    }

    private void deleteBottle(final BottleDto bottle) {
        bottleService.deleteBottle(bottle.getId(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(messages.deleteBottleError(bottle.getName(), exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, Void response) {
                NotificationUtils.info(messages.bottleDeleted(bottle.getName()));
            }
        });
    }

    @Override
    public void onAllBottlesStamped(AllBottlesStampedEvent event) {
        fetchData();
    }
}
