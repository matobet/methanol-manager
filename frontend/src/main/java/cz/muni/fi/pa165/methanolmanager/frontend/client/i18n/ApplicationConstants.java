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

    @DefaultStringValue("Create")
    String create();

    @DefaultStringValue("Edit")
    String edit();

    @DefaultStringValue("Delete")
    String delete();

    @DefaultStringValue("No stores added yet!")
    String noStoresYet();

    @DefaultStringValue("No producers added yet!")
    String noProducersYet();
    
    @DefaultStringValue("No bottles added yet!")
    String noBottlesYet();

    @DefaultStringValue("Name")
    String name();

    @DefaultStringValue("Address")
    String address();

    @DefaultStringValue("Cancel")
    String cancel();

    @DefaultStringValue("Save")
    String save();

    @DefaultStringValue("Make")
    String makeName();

    @DefaultStringValue("Producer")
    String producerName();

    @DefaultStringValue("Production date")
    String productionDate();

    @DefaultStringValue("Stamp date")
    String stampDate();
}
