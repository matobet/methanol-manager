package cz.muni.fi.pa165.methanolmanager.frontend.client.auth;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.ViewDef {

    interface Binder extends UiBinder<Row, LoginView> {
    }

    interface Driver extends SimpleBeanEditorDriver<UserDto, LoginView> {
    }

    @UiField
    @Path("name")
    TextBox nameEditor;

    @UiField
    @Path("password")
    Input passwordEditor;

    @UiField
    Button loginButton;

    private final Driver driver;

    @Inject
    public LoginView(Binder uiBinder, Driver driver) {
        this.driver = driver;
        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void edit(UserDto user) {
        driver.edit(user);
    }

    public UserDto flush() {
        return driver.flush();
    }

    @UiHandler("loginButton")
    void login(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onLogin(flush());
        }
    }
}
