package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import javax.inject.Inject;

public class MainView extends Composite implements MainPresenter.ViewDef {
    interface ViewUiBinder extends UiBinder<HTMLPanel, MainView> {
    }

    @Inject
    public MainView(ViewUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
