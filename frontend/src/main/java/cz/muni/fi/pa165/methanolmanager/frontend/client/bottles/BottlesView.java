/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

/**
 *
 * @author petr
 */

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
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class BottlesView extends ViewImpl implements BottlesPresenter.ViewDef {

    interface Binder extends UiBinder<Row, BottlesView> {
    }
    
    @UiField
    Button createButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;;

    @UiField
    CellTable<BottleDto> bottlesTable;

    @UiField
    Pagination bottlesPagination;

    MultiSelectionModel<BottleDto> selectionModel;

    private final ApplicationConstants applicationConstants;

    @Inject
    public BottlesView(Binder binder, ApplicationConstants applicationConstants) {
        this.applicationConstants = applicationConstants;
        
        initWidget(binder.createAndBindUi(this));
        initBottlesTable();
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
    public HasData<BottleDto> getBottlesTable() {
        return bottlesTable;
    }

    @Override
    public SetSelectionModel<BottleDto> getBottleTableSelection() {
        return selectionModel;
    }

    private void initBottlesTable() {
        bottlesTable.addColumn(new TextColumn<BottleDto>() {
            @Override
            public String getValue(BottleDto bottle) {
                return bottle.getId().toString();
            }
        }, new TextHeader("Id"));
        bottlesTable.addColumn(new TextColumn<BottleDto>() {
            @Override
            public String getValue(BottleDto bottle) {
                return bottle.getName();
            }
        }, new TextHeader("Name"));
        bottlesTable.addColumn(new TextColumn<BottleDto>() {
            @Override
            public String getValue(BottleDto bottle) {
                return bottle.getMakeName();
            }
        }, new TextHeader("Make"));
        bottlesTable.addColumn(new TextColumn<BottleDto>() {
            @Override
            public String getValue(BottleDto bottle) {
                return bottle.getProductionDate().toString();
            }
        }, new TextHeader("Production date"));
        bottlesTable.addColumn(new TextColumn<BottleDto>() {
            @Override
            public String getValue(BottleDto bottle) {
                return bottle.getStampDate().toString();
            }
        }, new TextHeader("Stamp date"));

        selectionModel = new MultiSelectionModel<>();
        bottlesTable.setSelectionModel(selectionModel);

        final SimplePager pager = new SimplePager();

        bottlesTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                bottlesPagination.rebuild(pager);
            }
        });

        bottlesTable.setLoadingIndicator(new ProgressBar());
        bottlesTable.setEmptyTableWidget(new Label(applicationConstants.noBottlesYet()));

        pager.setDisplay(bottlesTable);
        bottlesPagination.clear();
        bottlesPagination.rebuild(pager);
    }
}