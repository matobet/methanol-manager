package cz.muni.fi.pa165.methanolmanager.frontend.client.admin;

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
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.auth.AdminGatekeeper;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.UserService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.base.button.AbstractButton;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADED;
import static com.google.gwt.user.cellview.client.LoadingStateChangeEvent.LoadingState.LOADING;

/**
 *
 * @author Pavel Vomacka
 */
public class AdminPresenter extends Presenter<AdminPresenter.ViewDef, AdminPresenter.Proxy>  implements SelectionChangeEvent.Handler {

    public interface ViewDef extends View {
        AbstractButton getCreateButton();
        AbstractButton getEditButton();
        AbstractButton getDeleteButton();
        HasData<UserDto> getUsersTable();
        SetSelectionModel<UserDto> getUserTableSelection();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.ADMIN)
    @UseGatekeeper(AdminGatekeeper.class)
    public interface Proxy extends ProxyPlace<AdminPresenter> {
    }

    private ListDataProvider<UserDto> usersData;
    private final UserService userService;
    private final ApplicationMessages messages;
    private final UserPopupView userPopup;
    private UserDto editedItem;

    @Inject
    public AdminPresenter(EventBus eventBus, ViewDef view, Proxy proxy,
                            UserService userService, ApplicationMessages applicationMessages,
                            UserPopupView userPopupView) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);

        this.userService = userService;
        this.messages = applicationMessages;
        this.userPopup = userPopupView;
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getCreateButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                userPopup.setSubmitHandler(new UserPopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(UserDto user) {
                        createUser(user);
                    }
                });
                userPopup.edit(new UserDto());
                userPopup.show();
            }
        }));

        registerHandler(getView().getEditButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                userPopup.setSubmitHandler(new UserPopupView.SubmitHandler() {
                    @Override
                    public void onSubmit(UserDto user) {
                        updateUser(user);
                    }
                });
                editedItem = getView().getUserTableSelection().getSelectedSet().iterator().next();
                getView().getUserTableSelection().clear();
                userPopup.edit(editedItem);
                userPopup.show();
            }
        }));

        registerHandler(getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Set<UserDto> usersToDelete = getView().getUserTableSelection().getSelectedSet();
                getView().getUserTableSelection().clear();
                usersData.getList().removeAll(usersToDelete);
                for (UserDto user : usersToDelete) {
                    deleteUser(user);
                }
                usersData.flush();
            }
        }));

        registerHandler(getView().getUserTableSelection().addSelectionChangeHandler(this));

        usersData = new ListDataProvider<>();
        usersData.addDataDisplay(getView().getUsersTable());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fetchData();
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event) {
        Set<UserDto> selectedItems = getView().getUserTableSelection().getSelectedSet();
        getView().getEditButton().setEnabled(selectedItems.size() == 1);
        getView().getDeleteButton().setEnabled(selectedItems.size() > 0);
    }

    private void fetchData() {
        fireEvent(new LoadingStateChangeEvent(LOADING));
        userService.getUsers(new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(messages.loadUserError(exception.getLocalizedMessage()));
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }

            @Override
            public void onSuccess(Method method, List<UserDto> response) {
                usersData.setList(response);
                usersData.flush();
                fireEvent(new LoadingStateChangeEvent(LOADED));
            }
        });
    }

    private void createUser(final UserDto user) {
        userService.createUser(user, new MethodCallback<UserDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                NotificationUtils.error(messages.createUserError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, UserDto createdUser) {
                fetchData();
                NotificationUtils.info(messages.userCreated(createdUser.getUsername()));
            }
        });
    }

    private void updateUser(final UserDto user) {
        userService.updateUser(user.getId(), user, new MethodCallback<UserDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                NotificationUtils.error(messages.updateUserError(throwable.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, UserDto userDto) {
                fetchData();
                editedItem = null;
                NotificationUtils.info(messages.userUpdated(userDto.getUsername()));
            }
        });
    }

    private void deleteUser(final UserDto user) {
        userService.deleteUser(user.getId(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(messages.deleteUserError(user.getUsername(), exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, Void response) {
                NotificationUtils.info(messages.userDeleted(user.getUsername()));
            }
        });

    }
}
