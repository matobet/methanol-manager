package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pavel on 12.10.14.
 */
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

}