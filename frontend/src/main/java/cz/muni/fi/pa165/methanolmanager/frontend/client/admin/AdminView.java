package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.gwt.cell.client.ButtonCellBase;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.view.client.*;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.DefaultStringValueRenderer;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;

public class AdminView extends ViewImpl implements AdminPresenter.ViewDef {

    interface Binder extends UiBinder<Row, AdminView> {
    }

    @UiField
    TabListItem users;

    @UiField
    Button createButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;

    @UiField
    CellTable<UserDto> usersTable;

    @UiField
    Pagination usersPagination;

    MultiSelectionModel<UserDto> selectionModel;

    private final ApplicationConstants constants;

    @Inject
    public AdminView(Binder binder, ApplicationConstants constants) {

        this.constants = constants;

        initWidget(binder.createAndBindUi(this));
        initUsersTable();
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
    public HasData<UserDto> getUsersTable() {
        return usersTable;
    }

    @Override
    public SetSelectionModel<UserDto> getUserTableSelection() {
        return selectionModel;
    }

    private void initUsersTable() {
        usersTable.addColumn(new TextColumn<UserDto>() {
            @Override
            public String getValue(UserDto user) {
                return user.getId().toString();
            }
        }, new TextHeader(constants.id()));

        usersTable.addColumn(new TextColumn<UserDto>() {
            @Override
            public String getValue(UserDto user) {
                return user.getName();
            }
        }, new TextHeader(constants.name()));

        usersTable.addColumn(new TextColumn<UserDto>() {
            @Override
            public String getValue(UserDto user) {
                return user.getName();
            }
        }, new TextHeader(constants.role()));

        selectionModel = new MultiSelectionModel<>();
        usersTable.setSelectionModel(selectionModel);

        final SimplePager pager = new SimplePager();

        usersTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                usersPagination.rebuild(pager);
            }
        });

        usersTable.setLoadingIndicator(new ProgressBar());
        usersTable.setEmptyTableWidget(new Label(constants.noUsersYet()));

        pager.setDisplay(usersTable);
        usersPagination.clear();
        usersPagination.rebuild(pager);
    }
}