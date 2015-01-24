package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.MakeService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.StoreService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.DefaultStringValueRenderer;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    @UiField(provided = true)
    @Path("makeName")
    ValueListBox<String> makeEditor;

    @UiField(provided = true)
    @Path("storeName")
    ValueListBox<String> storeEditor;

    @UiField
    @Path("productionDate")
    DatePicker productionDateEditor;

    @UiField
    @Path("stampDate")
    DatePicker stampDateEditor;

    private final Driver driver;

    private final MakeService makeService;
    private final StoreService storeService;
    private final ApplicationConstants constants;
    private SubmitHandler submitHandler;

    @Inject
    public BottlePopupView(Binder binder, Driver driver, EventBus eventBus,
                           MakeService makeService, StoreService storeService,
                           final ApplicationConstants constants) {
        super(eventBus);
        this.driver = driver;
        this.makeService = makeService;
        this.storeService = storeService;
        this.constants = constants;
        initializeListBoxEditors();
        initWidget(binder.createAndBindUi(this));
        driver.initialize(this);
    }

    private void initializeListBoxEditors() {
        makeEditor = new ValueListBox<>(new DefaultStringValueRenderer(constants.selectMake()));
        storeEditor = new ValueListBox<>(new DefaultStringValueRenderer(constants.selectStore()));
    }

    public void show() {
        dialogBox.show();
    }

    public void edit(final BottleDto bottle) {
        makeService.getMakes(new MethodCallback<List<MakeDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(constants.errorLoadingMakes());
            }

            @Override
            public void onSuccess(Method method, List<MakeDto> response) {

                List<String> makeNames = new ArrayList<>();
                makeNames.add(null);
                for (MakeDto make : response) {
                    makeNames.add(make.getName());
                }
                makeEditor.setAcceptableValues(makeNames);

                storeService.getStores(new MethodCallback<List<StoreDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        NotificationUtils.error(constants.errorLoadingStores());
                    }

                    @Override
                    public void onSuccess(Method method, List<StoreDto> response) {
                        List<String> storeNames = new ArrayList<>();
                        storeNames.add(null);
                        for (StoreDto storeDto : response) {
                            storeNames.add(storeDto.getName());
                        }
                        storeEditor.setAcceptableValues(storeNames);

                        driver.edit(bottle);
                    }
                });
            }
        });
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
