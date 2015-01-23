package cz.muni.fi.pa165.methanolmanager.api.role;

import cz.muni.fi.pa165.methanolmanager.service.RoleService;
import cz.muni.fi.pa165.methanolmanager.service.dto.RoleDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zuzana on 1/23/2015.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Inject
    RoleService roleService;

    @RequestMapping(method = GET)
    public List<RoleDto> getProducers() {
        return roleService.getRoles();
    }
}
