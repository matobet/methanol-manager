package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {
    @DefaultMessage("Error loading stores: {0}")
    String loadStoreError(String error);

    @DefaultMessage("Error deleting store {0}: {1}")
    String deleteStoreError(String name, String error);

    @DefaultMessage("Store {0} sucessfully deleted!")
    String storeDeleted(String name);
}
