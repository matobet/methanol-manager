package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import cz.muni.fi.pa165.methanolmanager.frontend.client.about.AboutPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.PageDecorator;
import cz.muni.fi.pa165.methanolmanager.frontend.client.contact.ContactPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.welcome.WelcomePlace;
import fr.putnami.pwt.core.inject.client.Module;
import fr.putnami.pwt.core.inject.client.annotation.MvpDescription;
import fr.putnami.pwt.core.widget.client.OneWidgetPanel;
import fr.putnami.pwt.core.widget.client.OutputStaticText;

@MvpDescription(
        display = PageDecorator.class,
        defaultPlace = WelcomePlace.class,
        activities = {
                WelcomePlace.class,
                AboutPlace.class,
                ContactPlace.class
        }
)
public class GwtApp implements Module {
}
