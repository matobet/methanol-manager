package cz.muni.fi.pa165.methanolmanager.frontend.client.application;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import javax.inject.Inject;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.ViewDef, ApplicationPresenter.ProxyDef> {

    public interface ViewDef extends View {
    }

    @ProxyStandard
    public interface ProxyDef extends Proxy<ApplicationPresenter> {
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> MAIN_CONTENT = new GwtEvent.Type<>();

    @Inject
    public ApplicationPresenter(EventBus eventBus, ViewDef view, ProxyDef proxy) {
        super(eventBus, view, proxy, RevealType.RootLayout);
    }
}
