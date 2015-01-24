package cz.muni.fi.pa165.methanolmanager.frontend.client.make;

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
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;
import cz.muni.fi.pa165.methanolmanager.frontend.client.make.MakePopupView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.MakeService;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlHelper;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;
import static org.gwtbootstrap3.extras.growl.client.ui.Growl.growl;

public class MakesPresenter extends Presenter<MakesPresenter.ViewDef, MakesPresenter.Proxy> implements SelectionChangeEvent.Handler {

    public interface ViewDef extends View {
        AbstractButton getCreateButton();
        AbstractButton getEditButton();
        AbstractButton getDeleteButton();
        HasData<MakeDto> getMakeTable();
        SetSelectionModel<MakeDto> getMakeTableSelection();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.MAKES)
    public interface Proxy extends ProxyPlace<MakesPresenter> {
    }

    private ListDataProvider<MakeDto> makesData;
    private final MakeService makeService;
    private final ApplicationMessages messages;
    private final MakePopupView makePopup;
    private MakeDto editedItem;

    @Inject
    public MakesPresenter(EventBus eventBus, ViewDef view, Proxy proxy,
                          MakeService makeService, ApplicationMessages messages,
                          MakePopupView makePopup) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);

        this.makeService = makeService;
        this.messages = messages;
        this.makePopup = makePopup;
    }
    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getCreateButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                makePopup.setSubmitHandler(new MakePopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(MakeDto make) {
                        createMake(make);
                    }
                });
                makePopup.edit(new MakeDto());
                makePopup.show();
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                makePopup.setSubmitHandler(new MakePopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(MakeDto make) {
                        updateMake(make);
                    }
                });
                editedItem = getView().getMakeTableSelection().getSelectedSet().iterator().next();
                makePopup.edit(editedItem);
                makePopup.show();
            }
        }));

        registerHandler(getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Set<MakeDto> makesToDelete = getView().getMakeTableSelection().getSelectedSet();
                makesData.getList().removeAll(makesToDelete);
                for (MakeDto make : makesToDelete) {
                    deleteMake(make);
                }
                makesData.flush();
            }
        }));

        registerHandler(getView().getMakeTableSelection().addSelectionChangeHandler(this));

        makesData = new ListDataProvider<>();
        makesData.addDataDisplay(getView().getMakeTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
        onSelectionChange(null);
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event) {
        Set<MakeDto> selectedItems = getView().getMakeTableSelection().getSelectedSet();
        getView().getEditButton().setEnabled(selectedItems.size() == 1);
        getView().getDeleteButton().setEnabled(selectedItems.size() > 0);
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        makeService.getMakes(new MethodCallback<List<MakeDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.loadMakeError(exception.getLocalizedMessage()));
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }

            @Override
            public void onSuccess(Method method, List<MakeDto> response) {
                makesData.setList(response);
                makesData.flush();
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }
        });
    }

    private void createMake(final MakeDto make) {
        makeService.createMake(make, new MethodCallback<MakeDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                showError(messages.createMakeError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, MakeDto createdMake) {
                makesData.getList().add(createdMake);
                makesData.flush();
                growl(messages.makeCreated(createdMake.getName()));
            }
        });
    }

    private void updateMake(final MakeDto make) {
        makeService.updateMake(make.getId(), make, new MethodCallback<MakeDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                showError(messages.updateMakeError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, MakeDto makeDto) {
                makesData.getList().remove(editedItem);
                makesData.getList().add(makeDto);
                makesData.flush();
                makesData.refresh();
                editedItem = null;
                growl(messages.makeUpdated(makeDto.getName()));
            }
        });
    }

    private void deleteMake(final MakeDto make) {
        makeService.deleteMake(make.getId(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.deleteMakeError(make.getName(), exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, Void response) {
                growl(messages.makeDeleted(make.getName()));
            }
        });
    }

    private void showError(String error) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(error, options);
    }


}
