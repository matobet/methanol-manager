package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Role;
import cz.muni.fi.pa165.methanolmanager.dal.domain.User;
import cz.muni.fi.pa165.methanolmanager.dal.repository.RoleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.UserRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    Mapper mapper;

    @Override
    @Transactional

    public UserDto createUser(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        if (userDto.getRoleName() != null) {
            user.setRole(resolveRoleByName(userDto.getRoleName()));
        }

        userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    @Override

    public UserDto getUser(int userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new EntityNotFoundException(userId);
        }

        return mapper.map(user, UserDto.class);
    }

    @Override

    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(mapper.map(user, UserDto.class));
        }
        return users;
    }


    @Override
    @Transactional

    public void deleteUser(int userId){
        try {
            userRepository.delete(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(userId);
        }
    }

    @Override
    @Transactional

    public UserDto updateUser(UserDto userDto) {
        try {
            User user = userRepository.findOne(userDto.getId());
            mapper.map(userDto, user);
            if (userDto.getRoleName() != user.getRole().getName()) {
                user.setRole(resolveRoleByName(userDto.getRoleName()));
            }
            userRepository.save(user);

            return mapper.map(user, UserDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(userDto.getId());
        }
    }

    private Role resolveRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new EntityNotFoundException(roleName);
        }
        return role;
    }
}