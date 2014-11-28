package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.gwt.cell.client.ButtonCellBase;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;

public class AdminView extends ViewImpl implements AdminPresenter.ViewDef {

    interface Binder extends UiBinder<Row, AdminView> {
    }

    @Inject
    public AdminView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}