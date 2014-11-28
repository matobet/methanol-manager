package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;

import javax.inject.Inject;

public class AdminPresenter extends Presenter<AdminPresenter.ViewDef, AdminPresenter.Proxy> {

    public interface ViewDef extends View {

    }
    @ProxyCodeSplit
    @NameToken(NameTokens.ADMIN)
    public interface Proxy extends ProxyPlace<AdminPresenter> {
    }

    @Inject
    public AdminPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
    }
}
