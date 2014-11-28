package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {
    @DefaultMessage("Create bottle {0} of make {1}")
    String bottleCreated(String bottleName, String makeName);
}
