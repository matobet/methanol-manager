package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import org.fusesource.restygwt.client.Defaults;

public class ApplicationInit implements Bootstrapper {

    public static final String REST_ROOT_URL = "/api";

    @Inject
    PlaceManager placeManager;

    @Override
    public void onBootstrap() {
        Defaults.setServiceRoot(REST_ROOT_URL);

        placeManager.revealCurrentPlace();
    }
}
