package cz.muni.fi.pa165.methanolmanager.frontend.client.make;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;

import javax.inject.Inject;

public class MakePopupView extends PopupViewImpl implements Editor<MakeDto> {

    interface Binder extends UiBinder<Widget, MakePopupView> {
    }

    interface Driver extends SimpleBeanEditorDriver<MakeDto, MakePopupView> {
    }

    interface SubmitHandler {
        void onSubmit(MakeDto producer);
    }

    @UiField
    Modal dialogBox;

    @UiField
    @Path("name")
    TextBox nameEditor;

    @UiField
    @Path("producerName")
    TextBox producerNameEditor;

    private final Driver driver;
    private SubmitHandler submitHandler;

    @Inject
    public MakePopupView(Binder binder, Driver driver, EventBus eventBus) {
        super(eventBus);
        this.driver = driver;
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(MakeDto make) {
        driver.edit(make);
    }

    public MakeDto flush() {
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
