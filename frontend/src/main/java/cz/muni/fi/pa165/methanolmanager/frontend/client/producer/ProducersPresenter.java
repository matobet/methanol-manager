package cz.muni.fi.pa165.methanolmanager.frontend.client.producer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.LoadingStateChangeEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SetSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.ProducerService;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlHelper;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;
import static org.gwtbootstrap3.extras.growl.client.ui.Growl.growl;

public class ProducersPresenter extends Presenter<ProducersPresenter.ViewDef, ProducersPresenter.Proxy> implements SelectionChangeEvent.Handler {

    public interface ViewDef extends View {
        AbstractButton getCreateButton();
        AbstractButton getEditButton();
        AbstractButton getDeleteButton();
        HasData<ProducerDto> getProducerTable();
        SetSelectionModel<ProducerDto> getProducerTableSelection();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.PRODUCERS)
    public interface Proxy extends ProxyPlace<ProducersPresenter> {
    }

    private ListDataProvider<ProducerDto> producersData;
    private final ProducerService producerService;
    private final ApplicationMessages messages;
    private final ProducerPopupView producerPopup;
    private ProducerDto editedItem;

    @Inject
    public ProducersPresenter(EventBus eventBus, ViewDef view, Proxy proxy,
                              cz.muni.fi.pa165.methanolmanager.frontend.client.rest.ProducerService producerService, ApplicationMessages messages,
                              ProducerPopupView producerPopup) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);

        this.producerService = producerService;
        this.messages = messages;
        this.producerPopup = producerPopup;
    }
    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getCreateButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                producerPopup.setSubmitHandler(new ProducerPopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(ProducerDto producer) {
                        createProducer(producer);
                    }
                });
                producerPopup.edit(new ProducerDto());
                producerPopup.show();
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                producerPopup.setSubmitHandler(new ProducerPopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(ProducerDto producer) {
                        updateProducer(producer);
                    }
                });
                editedItem = getView().getProducerTableSelection().getSelectedSet().iterator().next();
                producerPopup.edit(editedItem);
                producerPopup.show();
            }
        }));

        registerHandler(getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Set<ProducerDto> producersToDelete = getView().getProducerTableSelection().getSelectedSet();
                producersData.getList().removeAll(producersToDelete);
                for (ProducerDto producer : producersToDelete) {
                    deleteProducer(producer);
                }
                producersData.flush();
            }
        }));

        registerHandler(getView().getProducerTableSelection().addSelectionChangeHandler(this));

        producersData = new ListDataProvider<>();
        producersData.addDataDisplay(getView().getProducerTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
        onSelectionChange(null);
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event) {
        Set<ProducerDto> selectedItems = getView().getProducerTableSelection().getSelectedSet();
        getView().getEditButton().setEnabled(selectedItems.size() == 1);
        getView().getDeleteButton().setEnabled(selectedItems.size() > 0);
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        producerService.getProducers(new MethodCallback<List<ProducerDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.loadProducerError(exception.getLocalizedMessage()));
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }

            @Override
            public void onSuccess(Method method, List<ProducerDto> response) {
                producersData.setList(response);
                producersData.flush();
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }
        });
    }

    private void createProducer(final ProducerDto producer) {
        producerService.createProducer(producer, new MethodCallback<ProducerDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                showError(messages.createProducerError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, ProducerDto createdProducer) {
                producersData.getList().add(createdProducer);
                producersData.flush();
                growl(messages.producerCreated(createdProducer.getName()));
            }
        });
    }

    private void updateProducer(final ProducerDto producer) {
        producerService.updateProducer(producer, new MethodCallback<ProducerDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                showError(messages.updateProducerError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, ProducerDto producerDto) {
                producersData.getList().remove(editedItem);
                producersData.getList().add(producerDto);
                producersData.flush();
                producersData.refresh();
                editedItem = null;
                growl(messages.producerUpdated(producerDto.getName()));
            }
        });
    }

    private void deleteProducer(final ProducerDto producer) {
        producerService.deleteProducer(producer.getId(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                showError(messages.deleteProducerError(producer.getName(), exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, Void response) {
                growl(messages.producerDeleted(producer.getName()));
            }
        });
    }

    private void showError(String error) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(error, options);
    }


}
