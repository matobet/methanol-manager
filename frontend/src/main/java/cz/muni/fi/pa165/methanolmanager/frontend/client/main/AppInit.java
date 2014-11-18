package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import org.fusesource.restygwt.client.Defaults;

public class AppInit implements Bootstrapper {

    @Inject
    PlaceManager placeManager;

    @Override
    public void onBootstrap() {
        Defaults.setServiceRoot(getRestApiServiceRoot());

        placeManager.revealCurrentPlace();
    }

    protected String getRestApiServiceRoot() {
        return "/api";
    }
}
