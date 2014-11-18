package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import cz.muni.fi.pa165.methanolmanager.frontend.client.ApplicationPlaces;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;

import javax.inject.Inject;
import java.util.List;

public class MainPresenter extends Presenter<MainPresenter.ViewDef, MainPresenter.Proxy> {

    public interface ViewDef extends View {
    }

    @ProxyStandard
    @NameToken(ApplicationPlaces.MAIN_PLACE)
    public interface Proxy extends ProxyPlace<MainPresenter> {
    }

    @Inject
    public MainPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }
}
