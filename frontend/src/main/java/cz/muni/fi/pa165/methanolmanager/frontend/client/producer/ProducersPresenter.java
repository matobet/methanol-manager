package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;

import javax.inject.Inject;

public class ProducersPresenter extends Presenter<ProducersPresenter.ViewDef, ProducersPresenter.Proxy> {

    public interface ViewDef extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.PRODUCERS)
    public interface Proxy extends ProxyPlace<ProducersPresenter> {
    }

    @Inject
    public ProducersPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
    }
}
