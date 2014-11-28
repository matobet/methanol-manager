package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Constants;

public interface ApplicationConstants extends Constants {

    @DefaultStringValue("Methanol Manager")
    String applicationName();

    @DefaultStringValue("Stores")
    String stores();

    @DefaultStringValue("Producers")
    String producers();

    @DefaultStringValue("Admin")
    String admin();
    
    @DefaultStringValue("Bottles")
    String bottles();
}
