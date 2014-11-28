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
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.LoadingStateChangeEvent;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
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
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import java.util.List;

import javax.inject.Inject;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import static org.gwtbootstrap3.extras.growl.client.ui.Growl.growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlHelper;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;

public class BottlesPresenter extends Presenter<BottlesPresenter.ViewDef, BottlesPresenter.Proxy> {

    public interface ViewDef extends View {
        HasClickHandlers getCreateButton();
        HasClickHandlers getEditButton();
        HasClickHandlers getDeleteButton();
        HasData<BottleDto> getBottlesTable();
    }

    @ProxyStandard
    @NameToken(NameTokens.BOTTLES)
    public interface Proxy extends ProxyPlace<BottlesPresenter> {
    }
    
    private ListDataProvider<BottleDto> bottlesData;
    private final BottleService bottleService;
    private final ApplicationMessages messages;

    @Inject
    public BottlesPresenter(EventBus eventBus, ViewDef view, Proxy proxy, 
            BottleService bottleService, ApplicationMessages applicationMessages) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
        
        this.bottleService = bottleService;
        this.messages = applicationMessages;
    }
    
    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getCreateButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // show popup
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // show popup
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // delete selected
            }
        }));

        bottlesData = new ListDataProvider<>();
        bottlesData.addDataDisplay(getView().getBottlesTable());
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        bottleService.getBottles(new MethodCallback<List<BottleDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.loadBottleError(exception.getLocalizedMessage()));
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

    private void showError(String error) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(error, options);
    }
}
