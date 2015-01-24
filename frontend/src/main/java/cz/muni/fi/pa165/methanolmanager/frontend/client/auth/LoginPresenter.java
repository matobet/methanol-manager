package cz.muni.fi.pa165.methanolmanager.frontend.client.auth;

import com.google.gwt.editor.client.Editor;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;

public class LoginPresenter extends Presenter<LoginPresenter.ViewDef, LoginPresenter.Proxy> implements LoginUiHandlers {

    public interface ViewDef extends View, HasUiHandlers<LoginUiHandlers>, Editor<UserDto> {
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.LOGIN)
    public interface Proxy extends ProxyPlace<LoginPresenter> {
    }

    private final CurrentUser currentUser;

    @Inject
    public LoginPresenter(EventBus eventBus, ViewDef view, Proxy proxy, CurrentUser currentUser) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
        this.currentUser = currentUser;
        getView().setUiHandlers(this);
    }

    @Override
    public void onLogin(UserDto user) {
        currentUser.login(user);
    }
}
