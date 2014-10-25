package cz.muni.fi.pa165.methanolmanager.dal.repository;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Petr Barton
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String name);
}
