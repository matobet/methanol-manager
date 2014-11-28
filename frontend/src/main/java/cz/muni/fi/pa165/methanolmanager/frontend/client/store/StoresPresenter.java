package cz.muni.fi.pa165.methanolmanager.frontend.client.store;

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
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.StoreService;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
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

public class StoresPresenter extends Presenter<StoresPresenter.ViewDef, StoresPresenter.Proxy> implements SelectionChangeEvent.Handler {

    public interface ViewDef extends View {
        AbstractButton getCreateButton();
        AbstractButton getEditButton();
        AbstractButton getDeleteButton();
        HasData<StoreDto> getStoreTable();
        SetSelectionModel<StoreDto> getStoreTableSelection();
    }

    @ProxyStandard
    @NameToken(NameTokens.STORES)
    public interface Proxy extends ProxyPlace<StoresPresenter> {
    }

    private ListDataProvider<StoreDto> storesData;
    private final StoreService storeService;
    private final ApplicationMessages messages;

    @Inject
    public StoresPresenter(EventBus eventBus, ViewDef view, Proxy proxy,
                           StoreService storeService, ApplicationMessages messages) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);

        this.storeService = storeService;
        this.messages = messages;
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

        registerHandler(getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Set<StoreDto> storesToDelete = getView().getStoreTableSelection().getSelectedSet();
                storesData.getList().removeAll(storesToDelete);
                for (StoreDto store : storesToDelete) {
                    deleteStore(store);
                }
                storesData.flush();
            }
        }));

        registerHandler(getView().getStoreTableSelection().addSelectionChangeHandler(this));

        storesData = new ListDataProvider<>();
        storesData.addDataDisplay(getView().getStoreTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
        onSelectionChange(null);
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event) {
        Set<StoreDto> selectedItems = getView().getStoreTableSelection().getSelectedSet();
        getView().getEditButton().setEnabled(selectedItems.size() == 1);
        getView().getDeleteButton().setEnabled(selectedItems.size() > 0);
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        storeService.getStores(new MethodCallback<List<StoreDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.loadStoreError(exception.getLocalizedMessage()));
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }

            @Override
            public void onSuccess(Method method, List<StoreDto> response) {
                storesData.setList(response);
                storesData.flush();
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }
        });
    }

    private void deleteStore(final StoreDto store) {
        storeService.deleteStore(store.getId(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.deleteStoreError(store.getName(), exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, Void response) {
                growl(messages.storeDeleted(store.getName()));
            }
        });
    }

    private void showError(String error) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(error, options);
    }

}
