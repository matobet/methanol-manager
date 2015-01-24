package cz.muni.fi.pa165.methanolmanager.frontend.client.auth;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationConstants;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.LoginService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.io.UnsupportedEncodingException;

public class CurrentUser {

    private final LoginService loginService;
    private final ApplicationConstants constants;
    private final ApplicationMessages messages;
    private UserDto user;

    @Inject
    public CurrentUser(LoginService loginService, ApplicationConstants constants, ApplicationMessages messages) {
        this.loginService = loginService;
        this.constants = constants;
        this.messages = messages;
    }

    public UserDto getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isAdmin() {
        return isLoggedIn() && "ROLE_ADMIN".equals(user.getRoleName());
    }

    public boolean isPolice() {
        return isLoggedIn() && "ROLE_POLICE".equals(user.getRoleName());
    }

    public void login(UserDto user) {
        loginService.login(createBasicAuthHeader(user.getName(), user.getPassword()), new MethodCallback<UserDto>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                NotificationUtils.error(messages.logInError(exception.getLocalizedMessage()));
            }

            @Override
            public void onSuccess(Method method, UserDto response) {
                CurrentUser.this.user = response;
                NotificationUtils.info(constants.logInSuccessful());
            }
        });
    }

    private static String createBasicAuthHeader(String userName, String password) {
        try {
            String credentials = userName + ":" + password;
            String encodedCredentials = new String(Base64.encode(credentials.getBytes()), "UTF-8");

            return "Basic " + encodedCredentials;
        } catch (UnsupportedEncodingException e) {
            GWT.log(e.getMessage(), e);
            return null;
        }
    }
}
