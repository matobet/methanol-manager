package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Role;
import cz.muni.fi.pa165.methanolmanager.dal.repository.RoleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.RoleDto;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

    @Inject
    RoleRepository roleRepository;

    @Inject
    Mapper mapper;

    @Override

    public List<RoleDto> getRoles() {
        List<RoleDto> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roles.add(mapper.map(role, RoleDto.class));
        }
        return roles;
    }
}
