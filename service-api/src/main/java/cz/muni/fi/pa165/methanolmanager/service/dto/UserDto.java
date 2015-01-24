package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zuzana on 1/23/2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    private String username;

    private String password;

    private String roleName;

}
