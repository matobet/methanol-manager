package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SetSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class ProducersView extends ViewImpl implements ProducersPresenter.ViewDef {

    interface Binder extends UiBinder<Row, ProducersView> {
    }

    @UiField
    Button createButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;;

    @UiField
    CellTable<ProducerDto> producersTable;

    @UiField
    Pagination producersPagination;

    MultiSelectionModel<ProducerDto> selectionModel;

    private final ApplicationConstants applicationConstants;

    @Inject
    public ProducersView(Binder binder, ApplicationConstants applicationConstants) {
        this.applicationConstants = applicationConstants;

        initWidget(binder.createAndBindUi(this));
        initProducersTable();
    }

    @Override
    public AbstractButton getCreateButton() {
        return createButton;
    }

    @Override
    public AbstractButton getEditButton() {
        return editButton;
    }

    @Override
    public AbstractButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public HasData<ProducerDto> getProducerTable() {
        return producersTable;
    }

    @Override
    public SetSelectionModel<ProducerDto> getProducerTableSelection() {
        return selectionModel;
    }

    private void initProducersTable() {
        producersTable.addColumn(new TextColumn<ProducerDto>() {
            @Override
            public String getValue(ProducerDto producer) {
                return producer.getId().toString();
            }
        }, new TextHeader("Id"));
        producersTable.addColumn(new TextColumn<ProducerDto>() {
            @Override
            public String getValue(ProducerDto producer) {
                return producer.getName();
            }
        }, new TextHeader("Name"));

        selectionModel = new MultiSelectionModel<>();
        producersTable.setSelectionModel(selectionModel);

        final SimplePager pager = new SimplePager();

        producersTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                producersPagination.rebuild(pager);
            }
        });

        producersTable.setLoadingIndicator(new ProgressBar());
        producersTable.setEmptyTableWidget(new Label(applicationConstants.noProducersYet()));

        pager.setDisplay(producersTable);
        producersPagination.clear();
        producersPagination.rebuild(pager);
    }
}