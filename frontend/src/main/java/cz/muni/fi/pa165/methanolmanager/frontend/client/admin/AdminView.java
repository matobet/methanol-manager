package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.gwt.cell.client.ButtonCellBase;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;

public class AdminView extends ViewImpl implements AdminPresenter.ViewDef {

    interface Binder extends UiBinder<Row, AdminView> {
    }

    @UiField
    CellTable<StoreDto> storesTable;

    @UiField
    Pagination storesPagination;

    ListDataProvider<StoreDto> storesData;

    @Inject
    public AdminView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        initStoresTable();
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

        pager.setDisplay(storesTable);
        storesPagination.clear();

        storesData = new ListDataProvider<>();
        storesData.getList().add(new StoreDto(1, "Bozkov s.r.o", "U vody, Brno 1"));
        storesData.getList().add(new StoreDto(2, "Finlandia s.r.o", "Sauna, Praha"));
        storesData.getList().add(new StoreDto(3, "Starobrno", "U patoka"));
        storesData.flush();
        storesPagination.rebuild(pager);

        storesData.addDataDisplay(storesTable);
    }
}