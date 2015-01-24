package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.UserService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.DefaultStringValueRenderer;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Vomacka
 */
public class UserPopupView extends PopupViewImpl implements Editor<UserDto> {

    interface Binder extends UiBinder<Widget, UserPopupView> {
    }

    interface Driver extends SimpleBeanEditorDriver<UserDto, UserPopupView> {
    }

    interface SubmitHandler {
        void onSubmit(UserDto user);
    }

    @UiField
    Modal dialogBox;

    @UiField
    @Path("username")
    TextBox nameEditor;

    @UiField
    @Path("password")
    TextBox passwdEditor;

    @UiField
    ListBox roleEditor;

    private final Driver driver;

    private final UserService userService;
    private final ApplicationConstants constants;
    private SubmitHandler submitHandler;

    @Inject
    public UserPopupView(Binder binder, Driver driver, EventBus eventBus,
                         UserService userService,
                         final ApplicationConstants constants) {
        super(eventBus);
        this.driver = driver;
        this.userService = userService;
        this.constants = constants;
        initializeListBoxEditors();
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    private void initializeListBoxEditors() {
        roleEditor = new ListBox ();
        roleEditor.addItem("AHOJ");
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(final UserDto user) {

    }

    public UserDto flush() {
        return driver.flush();
    }

    @UiHandler("submitButton")
    public void onSubmit(ClickEvent event) {
        if (submitHandler != null) {
            submitHandler.onSubmit(flush());
        }
        dialogBox.hide();
    }

    public SubmitHandler getSubmitHandler() {
        return submitHandler;
    }

    public void setSubmitHandler(SubmitHandler submitHandler) {
        this.submitHandler = submitHandler;
    }
}
