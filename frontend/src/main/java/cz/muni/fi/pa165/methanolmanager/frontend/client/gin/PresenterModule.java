package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import cz.muni.fi.pa165.methanolmanager.frontend.client.admin.AdminPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.admin.AdminView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.producer.ProducersPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.producer.ProducersView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.store.StoresPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.store.StoresView;

public class PresenterModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.ViewDef.class, ApplicationView.class, ApplicationPresenter.ProxyDef.class);
        bindPresenter(StoresPresenter.class, StoresPresenter.ViewDef.class, StoresView.class, StoresPresenter.Proxy.class);
        bindPresenter(AdminPresenter.class, AdminPresenter.ViewDef.class, AdminView.class, AdminPresenter.Proxy.class);
        bindPresenter(ProducersPresenter.class, ProducersPresenter.ViewDef.class, ProducersView.class, ProducersPresenter.Proxy.class);
    }
}
