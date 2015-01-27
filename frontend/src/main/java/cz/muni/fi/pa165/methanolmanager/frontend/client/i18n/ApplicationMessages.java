package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {

    @DefaultMessage("Error creating user: {0}")
    String createUserError(String error);

    @DefaultMessage("User {0} successfully created!")
    String userCreated(String name);

    @DefaultMessage("Error updating user: {0}")
    String updateUserError(String error);

    @DefaultMessage("User {0} successfully updated!")
    String userUpdated(String name);

    @DefaultMessage("Error loading user: {0}")
    String loadUserError(String error);

    @DefaultMessage("Error deleting user {0}: {1}")
    String deleteUserError(String name, String error);

    @DefaultMessage("User {0} successfully deleted!")
    String userDeleted(String name);

    @DefaultMessage("Error loading stores: {0}")
    String loadStoreError(String error);

    @DefaultMessage("Error deleting store {0}: {1}")
    String deleteStoreError(String name, String error);

    @DefaultMessage("Store {0} successfully deleted!")
    String storeDeleted(String name);
    
    @DefaultMessage("Error loading bottle: {0}")
    String loadBottleError(String error);

    @DefaultMessage("Error deleting bottle {0}: {1}")
    String deleteBottleError(String name, String error);

    @DefaultMessage("Bottle {0} successfully deleted!")
    String bottleDeleted(String name);

    @DefaultMessage("Error creating store: {0}")
    String createStoreError(String error);

    @DefaultMessage("Store {0} successfully created!")
    String storeCreated(String name);

    @DefaultMessage("Error updating store: {0}")
    String updateStoreError(String error);

    @DefaultMessage("Store {0} successfully updated!")
    String storeUpdated(String name);

    @DefaultMessage("Error updating producer: {0}")
    String updateProducerError(String error);

    @DefaultMessage("Error loading producers: {0}")
    String loadProducerError(String error);

    @DefaultMessage("Error deleting producer {0}: {1}")
    String deleteProducerError(String name, String error);

    @DefaultMessage("Producer {0} successfully deleted!")
    String producerDeleted(String name);

    @DefaultMessage("Error creating producer: {0}")
    String createProducerError(String error);

    @DefaultMessage("Producer {0} successfully created!")
    String producerCreated(String name);

    @DefaultMessage("Producer {0} successfully updated!")
    String producerUpdated(String name);

    @DefaultMessage("Error creating bottle: {0}")
    String createBottleError(String error);

    @DefaultMessage("Bottle {0} successfully created!")
    String bottleCreated(String name);

    @DefaultMessage("Error updating bottle: {0}")
    String updateBottleError(String error);

    @DefaultMessage("Bottle {0} successfully updated!")
    String bottleUpdated(String name);

    @DefaultMessage("Error updating make: {0}")
    String updateMakeError(String error);

    @DefaultMessage("Make {0} successfully created!")
    String makeCreated(String name);

    @DefaultMessage("Make {0} successfully updated!")
    String makeUpdated(String name);

    @DefaultMessage("Error creating make: {0}")
    String createMakeError(String error);

    @DefaultMessage("Error loading makes: {0}")
    String loadMakeError(String error);

    @DefaultMessage("Error deleting make {0}: {1}")
    String deleteMakeError(String name, String error);

    @DefaultMessage("Make {0} successfully deleted!")
    String makeDeleted(String name);

    @DefaultMessage("Error logging in: {0}")
    String logInError(String error);

    @DefaultMessage("Bottle {0} successfully stamped!")
    String bottleStamped(String name);

    @DefaultMessage("Error stamping bottle {0}: {1}")
    String stampBottleError(String name, String error);

    @DefaultMessage("Stamped on: {0}")
    String stampedOn(String date);
}
