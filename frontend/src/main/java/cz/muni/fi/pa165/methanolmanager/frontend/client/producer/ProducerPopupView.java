package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;

import javax.inject.Inject;

/**
 * @author Martin Betak
 */
public class ProducerPopupView extends PopupViewImpl implements Editor<ProducerDto> {

    interface Binder extends UiBinder<Widget, ProducerPopupView> {
    }

    interface Driver extends SimpleBeanEditorDriver<ProducerDto, ProducerPopupView> {
    }

    interface SubmitHandler {
        void onSubmit(ProducerDto producer);
    }

    @UiField
    Modal dialogBox;

    @UiField
    @Path("name")
    TextBox nameEditor;

    private final Driver driver;
    private SubmitHandler submitHandler;

    @Inject
    public ProducerPopupView(Binder binder, Driver driver, EventBus eventBus) {
        super(eventBus);
        this.driver = driver;
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(ProducerDto producer) {
        driver.edit(producer);
    }

    public ProducerDto flush() {
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
