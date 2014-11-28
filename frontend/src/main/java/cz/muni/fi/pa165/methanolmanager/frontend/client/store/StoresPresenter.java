package cz.muni.fi.pa165.methanolmanager.frontend.client.store;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.LoadingStateChangeEvent;
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
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.StoreService;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlHelper;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;

import javax.inject.Inject;

import java.util.List;

import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;
import static org.gwtbootstrap3.extras.growl.client.ui.Growl.growl;

public class StoresPresenter extends Presenter<StoresPresenter.ViewDef, StoresPresenter.Proxy> {

    public interface ViewDef extends View {
        HasClickHandlers getCreateButton();
        HasClickHandlers getEditButton();
        HasClickHandlers getDeleteButton();
        HasData<StoreDto> getStoreTable();
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

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // delete selected
            }
        }));

        storesData = new ListDataProvider<>();
        storesData.addDataDisplay(getView().getStoreTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        storeService.getStores(new MethodCallback<List<StoreDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.loadError(exception.getLocalizedMessage()));
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

    private void showError(String error) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(error, options);
    }
}
