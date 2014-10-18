package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.event.BaseEventHandler;
import org.fusesource.restygwt.client.Defaults;

@EventHandler
public final class Bootstrap extends BaseEventHandler<MainEventBus> {
    static {
        Defaults.setServiceRoot("/api");
    }

    public void onApplicationLoad() {
    }
}
