package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import java.util.List;

public class MainView extends ViewImpl implements MainPresenter.ViewDef {
    interface ViewUiBinder extends UiBinder<HTMLPanel, MainView> {
    }

    @UiField
    ListBox listBox;

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
}