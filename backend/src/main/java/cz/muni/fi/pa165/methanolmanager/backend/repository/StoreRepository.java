package cz.muni.fi.pa165.methanolmanager.backend.repository;

import cz.muni.fi.pa165.methanolmanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Petr Barton
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String name);
}
