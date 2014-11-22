package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.store.StoresPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.store.StoresView;

public class PresenterModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.ViewDef.class, ApplicationView.class, ApplicationPresenter.ProxyDef.class);
        bindPresenter(StoresPresenter.class, StoresPresenter.ViewDef.class, StoresView.class, StoresPresenter.Proxy.class);
    }
}
