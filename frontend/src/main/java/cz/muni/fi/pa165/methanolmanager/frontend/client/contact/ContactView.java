package cz.muni.fi.pa165.methanolmanager.frontend.client.contact;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import cz.muni.fi.pa165.methanolmanager.frontend.shared.domain.Contact;
import fr.putnami.pwt.core.editor.client.event.FlushSuccessEvent;
import fr.putnami.pwt.core.inject.client.annotation.Initialize;
import fr.putnami.pwt.core.inject.client.annotation.Templated;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.widget.client.Form;

import java.util.Arrays;
import java.util.List;

@Templated
public class ContactView extends Composite implements View {
    @UiField(provided = true)
    List<Integer> noteItems = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @UiField(provided = true)
    List<String> subjectItems = Arrays.asList("About this tutorial", "About PWT", "About Putnami Team", "Other");

    @UiField
    @Initialize
    Form<Contact> contactEditor;

    @UiHandler("contactEditor")
    public void onContactSubmit(FlushSuccessEvent event) {
        Window.alert(event.<Contact>getValue().getEmail());
    }
}
