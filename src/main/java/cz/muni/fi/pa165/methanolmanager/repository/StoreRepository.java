package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Petr Barton
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String name);
}
