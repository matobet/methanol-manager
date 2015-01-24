package cz.muni.fi.pa165.methanolmanager.frontend.client.auth;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class AdminGatekeeper implements Gatekeeper {

    private final CurrentUser currentUser;

    @Inject
    AdminGatekeeper(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean canReveal() {
        return currentUser.isAdmin();
    }
}
