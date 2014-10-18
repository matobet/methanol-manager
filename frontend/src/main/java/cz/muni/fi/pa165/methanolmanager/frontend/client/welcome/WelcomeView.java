package cz.muni.fi.pa165.methanolmanager.frontend.client.welcome;

import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import cz.muni.fi.pa165.methanolmanager.frontend.client.about.AboutPlace;
import fr.putnami.pwt.core.inject.client.annotation.Templated;
import fr.putnami.pwt.core.mvp.client.MvpController;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.widget.client.OutputStaticText;
import fr.putnami.pwt.core.widget.client.event.ButtonEvent;

@Templated
public class WelcomeView extends Composite implements View {
    @UiHandler("aboutButton")
    public void onAboutButton(ButtonEvent event) {
        MvpController.get().goTo(new AboutPlace());
    }
}
