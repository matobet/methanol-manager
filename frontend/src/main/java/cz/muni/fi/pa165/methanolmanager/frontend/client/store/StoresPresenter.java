package cz.muni.fi.pa165.methanolmanager.frontend.client.store;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;

import javax.inject.Inject;

public class StoresPresenter extends Presenter<StoresPresenter.ViewDef, StoresPresenter.Proxy> {

    public interface ViewDef extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.STORES)
    public interface Proxy extends ProxyPlace<StoresPresenter> {
    }

    @Inject
    public StoresPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
    }
}
