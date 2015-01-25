package cz.muni.fi.pa165.methanolmanager.api;

import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zuzana on 1/24/2015.
 */
//@RestController
//@RequestMapping("/login")
//public class LoginController {
//
//    @RequestMapping(method = GET)
//    public UserDto login() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDto userDto = new UserDto();
//        userDto.setUsername(user.getUsername());
//        userDto.setRoleName(String.valueOf(user.getAuthorities()));
//        return userDto;
//    }
//}
