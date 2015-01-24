package cz.muni.fi.pa165.methanolmanager.api.user;

import cz.muni.fi.pa165.methanolmanager.service.UserService;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zuzana on 1/23/2015.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Inject
    UserService userService;

    @RequestMapping("/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @RequestMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public UserDto updateUser(@PathVariable("id") int id, @RequestBody UserDto user) {
        return userService.updateUser(user);
    }
}
