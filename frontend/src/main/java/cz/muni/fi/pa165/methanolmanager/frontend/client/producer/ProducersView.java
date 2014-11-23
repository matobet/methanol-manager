package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Row;

public class ProducersView extends ViewImpl implements ProducersPresenter.ViewDef {

    interface Binder extends UiBinder<Row, ProducersView> {
    }

    @Inject
    public ProducersView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}