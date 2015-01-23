package cz.muni.fi.pa165.methanolmanager.dal.repository;

import cz.muni.fi.pa165.methanolmanager.dal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zuzana on 1/23/2015.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
