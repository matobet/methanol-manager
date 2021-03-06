package cz.muni.fi.pa165.methanolmanager.dal.repository;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
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

    @Query("select count(b) from Bottle b where b.toxic = false and b.make.producer = ?1")
    long countNotToxicByProducer(Producer producer);

    @Query("select count(b) from Bottle b where b.toxic = true and b.make = ?1")
    long countToxicByMake(Make make);

    @Query("select count(b) from Bottle b where b.toxic = true and b.store = ?1")
    long countToxicByStore(Store store);
}
