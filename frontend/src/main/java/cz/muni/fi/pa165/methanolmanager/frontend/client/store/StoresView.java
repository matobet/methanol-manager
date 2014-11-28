package cz.muni.fi.pa165.methanolmanager.frontend.client.store;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class StoresView extends ViewImpl implements StoresPresenter.ViewDef {

    interface Binder extends UiBinder<Row, StoresView> {
    }

    @UiField
    Button createButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;;

    @UiField
    CellTable<StoreDto> storesTable;

    @UiField
    Pagination storesPagination;

    private final ApplicationConstants applicationConstants;

    @Inject
    public StoresView(Binder binder, ApplicationConstants applicationConstants) {
        this.applicationConstants = applicationConstants;

        initWidget(binder.createAndBindUi(this));
        initStoresTable();
    }

    @Override
    public HasClickHandlers getCreateButton() {
        return createButton;
    }

    @Override
    public HasClickHandlers getEditButton() {
        return editButton;
    }

    @Override
    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    @Override
    public HasData<StoreDto> getStoreTable() {
        return storesTable;
    }

    private void initStoresTable() {
        storesTable.addColumn(new TextColumn<StoreDto>() {
            @Override
            public String getValue(StoreDto store) {
                return store.getId().toString();
            }
        }, new TextHeader("Id"));
        storesTable.addColumn(new TextColumn<StoreDto>() {
            @Override
            public String getValue(StoreDto store) {
                return store.getName();
            }
        }, new TextHeader("Name"));
        storesTable.addColumn(new TextColumn<StoreDto>() {
            @Override
            public String getValue(StoreDto store) {
                return store.getAddress();
            }
        }, new TextHeader("Address"));

        final SimplePager pager = new SimplePager();

        storesTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                storesPagination.rebuild(pager);
            }
        });

        storesTable.setLoadingIndicator(new ProgressBar());
        storesTable.setEmptyTableWidget(new Label(applicationConstants.noStoresYet()));

        pager.setDisplay(storesTable);
        storesPagination.clear();
        storesPagination.rebuild(pager);
    }
}