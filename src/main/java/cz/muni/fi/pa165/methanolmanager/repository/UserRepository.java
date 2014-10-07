package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
