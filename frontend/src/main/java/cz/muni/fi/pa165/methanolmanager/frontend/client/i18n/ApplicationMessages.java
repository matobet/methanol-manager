package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {
    @DefaultMessage("Error loading stores: {0}")
    String loadStoreError(String error);

    @DefaultMessage("Error deleting store {0}: {1}")
    String deleteStoreError(String name, String error);

    @DefaultMessage("Store {0} sucessfully deleted!")
    String storeDeleted(String name);
    
    @DefaultMessage("Error loading bottle: {0}")
    String loadBottleError(String error);

    @DefaultMessage("Error deleting bottle {0}: {1}")
    String deleteBottleError(String name, String error);

    @DefaultMessage("Bottle {0} sucessfully deleted!")
    String bottleDeleted(String name);

    @DefaultMessage("Error creating store: {0}")
    String createStoreError(String error);

    @DefaultMessage("Store {0} sucessfully created!")
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

    @DefaultMessage("Producer {0} sucessfully deleted!")
    String producerDeleted(String name);

    @DefaultMessage("Error creating producer: {0}")
    String createProducerError(String error);

    @DefaultMessage("Producer {0} sucessfully created!")
    String producerCreated(String name);

    @DefaultMessage("Producer {0} successfully updated!")
    String producerUpdated(String name);

    @DefaultMessage("Error creating bottle: {0}")
    String createBottleError(String error);

    @DefaultMessage("Bottle {0} sucessfully created!")
    String bottleCreated(String name);

    @DefaultMessage("Error updating bottle: {0}")
    String updateBottleError(String error);

    @DefaultMessage("Bottle {0} successfully updated!")
    String bottleUpdated(String name);
}
