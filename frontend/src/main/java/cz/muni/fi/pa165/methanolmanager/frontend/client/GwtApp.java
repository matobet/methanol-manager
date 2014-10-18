package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import fr.putnami.pwt.core.widget.client.OutputStaticText;

public class GwtApp implements EntryPoint {
    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new OutputStaticText("Hello Putnami"));
    }
}
