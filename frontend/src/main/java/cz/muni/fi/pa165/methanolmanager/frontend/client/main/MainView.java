package cz.muni.fi.pa165.methanolmanager.frontend.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class MainView extends ViewImpl implements MainPresenter.ViewDef {
    interface ViewUiBinder extends UiBinder<HTMLPanel, MainView> {
    }

    @Inject
    public MainView(ViewUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}