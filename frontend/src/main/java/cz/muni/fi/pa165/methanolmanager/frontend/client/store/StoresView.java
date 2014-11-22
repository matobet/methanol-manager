package cz.muni.fi.pa165.methanolmanager.frontend.client.store;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Row;

public class StoresView extends ViewImpl implements StoresPresenter.ViewDef {

    interface Binder extends UiBinder<Row, StoresView> {
    }

    @Inject
    public StoresView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}