package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
public interface UserService {
    @Secured({"ROLE_ADMIN"})
    UserDto createUser(UserDto userDto);

    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    UserDto getUser(int userId);

    List<UserDto> getUsers();

    @Secured({"ROLE_ADMIN"})
    void deleteUser(int userId);

    @Secured({"ROLE_ADMIN"})
    UserDto updateUser(UserDto userDto);
}
