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
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.ProducerService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.DefaultStringValueRenderer;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    @UiField(provided = true)
    @Path("producerName")
    ValueListBox<String> producerEditor;

    private final Driver driver;
    private SubmitHandler submitHandler;

    private final ProducerService producerService;
    private final ApplicationConstants applicationConstants;

    @Inject
    public MakePopupView(Binder binder, Driver driver, EventBus eventBus, ProducerService producerService,
                         final ApplicationConstants applicationConstants) {
        super(eventBus);
        this.driver = driver;
        this.producerService = producerService;

        this.applicationConstants = applicationConstants;
        producerEditor = new ValueListBox<>(new DefaultStringValueRenderer(applicationConstants.selectProducer()));
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(final MakeDto make) {
        producerService.getProducers(new MethodCallback<List<ProducerDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(applicationConstants.errorLoadingProducers());
            }

            @Override
            public void onSuccess(Method method, List<ProducerDto> response) {

                List<String> producerNames = new ArrayList<>();
                producerNames.add(null);
                for (ProducerDto producer : response) {
                    producerNames.add(producer.getName());
                }
                producerEditor.setAcceptableValues(producerNames);

                driver.edit(make);
            }
        });
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
