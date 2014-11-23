package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Row;

import javax.inject.Inject;

public class AdminView extends ViewImpl implements AdminPresenter.ViewDef {

    interface Binder extends UiBinder<Row, AdminView> {
    }

    @Inject
    public AdminView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}