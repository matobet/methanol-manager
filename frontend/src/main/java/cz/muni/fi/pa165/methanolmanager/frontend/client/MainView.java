package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.ListBox;

import javax.inject.Inject;
import java.util.List;

public class MainView extends Composite implements MainPresenter.ViewDef {

    interface ViewUiBinder extends UiBinder<HTMLPanel, MainView> {
    }

    @UiField
    ListBox listBox;

    @UiField
    Label errorLabel;

    @Inject
    public MainView(ViewUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setBottles(List<String> bottles) {
        for (String bottle : bottles) {
            listBox.addItem(bottle);
        }
    }

    @Override
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }
}
