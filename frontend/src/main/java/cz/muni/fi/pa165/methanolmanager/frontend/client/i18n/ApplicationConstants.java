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

    @DefaultStringValue("Here you can add and remove users.")
    String adminUsersHeader();

    @DefaultStringValue("Users")
    String users();

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

    @DefaultStringValue("Add user:")
    String addUser();

    @DefaultStringValue("Password")
    String passwd();

    @DefaultStringValue("Repeat password")
    String passwdRep();

    @DefaultStringValue("Remove user:")
    String removeUser();
}
