package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;

import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(int userId);

    List<UserDto> getUsers();

    void deleteUser(int userId);

    UserDto updateUser(UserDto userDto);
}
