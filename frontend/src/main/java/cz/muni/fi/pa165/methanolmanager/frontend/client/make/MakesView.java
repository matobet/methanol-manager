package cz.muni.fi.pa165.methanolmanager.frontend.client.make;

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
import cz.muni.fi.pa165.methanolmanager.frontend.client.producer.ProducersPresenter;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class MakesView extends ViewImpl implements MakesPresenter.ViewDef {

    interface Binder extends UiBinder<Row, MakesView> {
    }

    @UiField
    Button createButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;

    @UiField
    CellTable<MakeDto> makesTable;

    @UiField
    Pagination makesPagination;

    MultiSelectionModel<MakeDto> selectionModel;

    private final ApplicationConstants applicationConstants;

    @Inject
    public MakesView(Binder binder, ApplicationConstants applicationConstants) {
        this.applicationConstants = applicationConstants;

        initWidget(binder.createAndBindUi(this));
        initMakesTable();
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
    public HasData<MakeDto> getMakeTable() {
        return makesTable;
    }

    @Override
    public SetSelectionModel<MakeDto> getMakeTableSelection() {
        return selectionModel;
    }

    private void initMakesTable() {
        makesTable.addColumn(new TextColumn<MakeDto>() {
            @Override
            public String getValue(MakeDto make) {
                return make.getId().toString();
            }
        }, new TextHeader("Id"));
        makesTable.addColumn(new TextColumn<MakeDto>() {
            @Override
            public String getValue(MakeDto make) {
                return make.getName();
            }
        }, new TextHeader("Name"));
        makesTable.addColumn(new TextColumn<MakeDto>() {
            @Override
            public String getValue(MakeDto make) {
                return make.getProducerName();
            }
        }, new TextHeader("Producer name"));

        selectionModel = new MultiSelectionModel<>();
        makesTable.setSelectionModel(selectionModel);

        final SimplePager pager = new SimplePager();

        makesTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                makesPagination.rebuild(pager);
            }
        });

        makesTable.setLoadingIndicator(new ProgressBar());
        makesTable.setEmptyTableWidget(new Label(applicationConstants.noMakesYet()));

        pager.setDisplay(makesTable);
        makesPagination.clear();
        makesPagination.rebuild(pager);
    }
}