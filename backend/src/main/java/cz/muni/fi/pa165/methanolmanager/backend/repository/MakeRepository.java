package cz.muni.fi.pa165.methanolmanager.backend.repository;

import cz.muni.fi.pa165.methanolmanager.backend.domain.Make;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Martin Betak
 */
public interface MakeRepository extends JpaRepository<Make, Integer> {

    Make findByName(String name);

}