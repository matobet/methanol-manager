package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BottleRepository extends JpaRepository<Bottle, Integer> {
    Bottle findByName(String name);
    List<Bottle> findByToxicTrue();

    @Query("select b from Bottle b where b.stampDate != null")
    List<Bottle> findAllStamped();

    Bottle findByName(String name);
}
