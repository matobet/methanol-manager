package cz.muni.fi.pa165.methanolmanager.frontend.client.auth;

import com.gwtplatform.mvp.client.UiHandlers;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;

public interface LoginUiHandlers extends UiHandlers {
    void onLogin(UserDto user);
}
