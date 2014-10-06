package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BottleRepository extends JpaRepository<Bottle, Integer> {
    List<Bottle> findByToxicTrue();
}
