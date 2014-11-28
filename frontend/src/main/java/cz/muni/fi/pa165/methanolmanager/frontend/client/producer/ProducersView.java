package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.gwtbootstrap3.client.ui.Row;

import java.util.List;

public class ProducersView extends ViewImpl implements ProducersPresenter.ViewDef {

    interface Binder extends UiBinder<Row, ProducersView> {
    }

    @Inject
    public ProducersView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}