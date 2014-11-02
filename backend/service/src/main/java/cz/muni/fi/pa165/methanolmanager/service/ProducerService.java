package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;

/**
 * @author Petr Barton
 */

@Service
@Transactional (readOnly = true)
public class ProducerService {

    @Inject
    ProducerRepository producerRepository;

    @Inject
    Mapper mapper;

    @Transactional
    public void createProducer(ProducerDto producerDto){
        Producer producer = mapper.map(producerDto, Producer.class);

        producerRepository.save(producer);
    }

    public int producedToxicBottles(){
        throw new NotImplementedException();
    }

    public int producedNonToxicBottles(){
        throw new NotImplementedException();
    }


    @Transactional
    public void deleteProducer(int producerId){
        try{
            producerRepository.delete(producerId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(producerId);
        }
    }
}
