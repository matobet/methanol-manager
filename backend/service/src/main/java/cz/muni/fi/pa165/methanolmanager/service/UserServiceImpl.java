package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Role;
import cz.muni.fi.pa165.methanolmanager.dal.domain.User;
import cz.muni.fi.pa165.methanolmanager.dal.repository.UserRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.UserDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserServiceImpl implements UserService, UserDetailsService {

    @Inject
    UserRepository userRepository;

    @Inject
    Mapper mapper;

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public UserDto createUser(UserDto userDto){
        User user = mapper.map(userDto, User.class);

        userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    public UserDto getUser(int userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new EntityNotFoundException(userId);
        }

        return mapper.map(user, UserDto.class);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(mapper.map(user, UserDto.class));
        }
        return users;
    }


    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public void deleteUser(int userId){
        try {
            userRepository.delete(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(userId);
        }
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public UserDto updateUser(UserDto userDto) {
        try {
            User user = userRepository.findOne(userDto.getId());
            mapper.map(userDto, user);
            userRepository.save(user);

            return mapper.map(user, UserDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(userDto.getId());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userRepository.findByUsername(username);

            if(user == null){
                throw new UsernameNotFoundException(username);
            }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(final Role role: user.getRoles()){
            authorities.add(new GrantedAuthority() {

                @Override
                public String getAuthority() {
                    return role.getName();
                }
            });
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
            return userDetails;
        }

}