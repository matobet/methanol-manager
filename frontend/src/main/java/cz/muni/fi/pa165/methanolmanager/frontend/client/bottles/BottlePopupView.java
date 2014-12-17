package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;

import javax.inject.Inject;

/**
 * @author petr
 */
public class BottlePopupView extends PopupViewImpl implements Editor<BottleDto> {
    interface Binder extends UiBinder<Widget, BottlePopupView> {
    }

    interface Driver extends SimpleBeanEditorDriver<BottleDto, BottlePopupView> {
    }

    interface SubmitHandler {
        void onSubmit(BottleDto bottle);
    }

    @UiField
    Modal dialogBox;

    @UiField
    @Path("name")
    TextBox nameEditor;

    @UiField
    @Path("makeName")
    ListBox makeEditor;

    @UiField
    @Path("productionDate")
    DatePicker productionDateEditor;

    @UiField
    @Path("stampDate")
    DatePicker stampDateEditor;

    private final Driver driver;
    private SubmitHandler submitHandler;

    @Inject
    public BottlePopupView(Binder binder, Driver driver, EventBus eventBus) {
        super(eventBus);
        this.driver = driver;
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(BottleDto bottle) {
        driver.edit(bottle);
    }

    public BottleDto flush() {
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
