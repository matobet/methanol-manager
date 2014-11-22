package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import cz.muni.fi.pa165.methanolmanager.frontend.client.ApplicationPlaces;

import javax.inject.Inject;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.ViewDef, ApplicationPresenter.Proxy> {

    public interface ViewDef extends View {
    }

    @ProxyStandard
    @NameToken(ApplicationPlaces.MAIN_PLACE)
    public interface Proxy extends ProxyPlace<ApplicationPresenter> {
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> MAIN_CONTENT = new GwtEvent.Type<>();

    @Inject
    public ApplicationPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.RootLayout);
    }
}
