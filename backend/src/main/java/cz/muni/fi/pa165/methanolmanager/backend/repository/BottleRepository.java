package cz.muni.fi.pa165.methanolmanager.backend.repository;

import cz.muni.fi.pa165.methanolmanager.backend.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.backend.domain.Make;
import cz.muni.fi.pa165.methanolmanager.backend.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * @author Pavel Vomacka
 */
public interface BottleRepository extends JpaRepository<Bottle, Integer> {
    Bottle findByName(String name);

    @Query("select b from Bottle b where b.toxic = true")
    List<Bottle> findAllToxic();

    @Query("select b from Bottle b where b.stampDate is not null")
    List<Bottle> findAllStamped();

    @Query("select count(b) from Bottle b where b.toxic = true and b.make.producer = ?1")
    long countToxicByProducer(Producer producer);

    @Query("select count(b) from Bottle b where b.toxic = true and b.make = ?1")
    long countToxicByMake(Make make);

    @Query("select count(b) from Bottle b where b.toxic = true and b.store = ?1")
    long countToxicByStore(Store store);
}
