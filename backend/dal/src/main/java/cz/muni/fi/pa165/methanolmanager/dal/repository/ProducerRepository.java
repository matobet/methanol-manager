package cz.muni.fi.pa165.methanolmanager.dal.repository;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Zuzana Melsova
 */
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    Producer findByName(String name);

}