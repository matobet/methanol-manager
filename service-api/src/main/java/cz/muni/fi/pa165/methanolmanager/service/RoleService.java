package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.RoleDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
public interface RoleService {

    @Secured({"ROLE_ADMIN"})
    List<RoleDto> getRoles();

}
