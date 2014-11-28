package cz.muni.fi.pa165.methanolmanager.frontend.client.i18n;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {
    @DefaultMessage("Error fetching data: {0}")
    String loadError(String error);
}
