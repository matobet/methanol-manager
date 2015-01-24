package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petr Barton
 */

@Service
@Transactional(readOnly = true)
public class ProducerServiceImpl implements ProducerService {

    @Inject
    ProducerRepository producerRepository;

    @Inject
    BottleRepository bottleRepository;

    @Inject
    Mapper mapper;

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public ProducerDto createProducer(ProducerDto producerDto) {
        Producer producer = mapper.map(producerDto, Producer.class);

        producerRepository.save(producer);

        return mapper.map(producer, ProducerDto.class);
    }

    @Override
    public ProducerDto getProducer(int producerId) {
        Producer producer = producerRepository.findOne(producerId);
        if (producer == null) {
            throw new EntityNotFoundException(producerId);
        }

        ProducerDto producerDto = mapper.map(producer, ProducerDto.class);
        producerDto.setProducedToxicBottles(bottleRepository.countToxicByProducer(producer));

        return producerDto;
    }

    @Override
    public List<ProducerDto> getProducers() {
        List<ProducerDto> producers = new ArrayList<>();
        for (Producer producer : producerRepository.findAll()) {
            ProducerDto producerDto = mapper.map(producer, ProducerDto.class);
            producerDto.setProducedToxicBottles(bottleRepository.countToxicByProducer(producer));

            producers.add(producerDto);
        }
        return producers;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    public long countProducedToxicBottles(int producerId) {
        Producer producer = producerRepository.findOne(producerId);

        if (producer == null) {
            throw new EntityNotFoundException(producerId);
        }

        return bottleRepository.countToxicByProducer(producer);
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    public long countProducedNonToxicBottles(int producerId) {
        Producer producer = producerRepository.findOne(producerId);

        if (producer == null) {
            throw new EntityNotFoundException(producerId);
        }

        return bottleRepository.countNotToxicByProducer(producer);
    }


    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public void deleteProducer(int producerId) {
        try {
            producerRepository.delete(producerId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(producerId);
        }
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public ProducerDto updateProducer(ProducerDto producerDto) {
        try {
            Producer producer = producerRepository.findOne(producerDto.getId());
            mapper.map(producerDto, producer);
            producerRepository.save(producer);

            return mapper.map(producer, ProducerDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(producerDto.getId());
        }
    }
}
