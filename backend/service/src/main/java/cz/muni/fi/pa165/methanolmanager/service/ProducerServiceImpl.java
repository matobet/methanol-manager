package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
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

        return mapper.map(producer, ProducerDto.class);
    }

    @Override
    public List<ProducerDto> getProducers() {
        List<ProducerDto> producers = new ArrayList<>();
        for (Producer producer : producerRepository.findAll()) {
            producers.add(mapper.map(producer, ProducerDto.class));
        }
        return producers;
    }

    @Override
    public long countProducedToxicBottles(int producerId) {
        Producer producer = producerRepository.findOne(producerId);

        if (producer == null) {
            throw new EntityNotFoundException(producerId);
        }

        return bottleRepository.countToxicByProducer(producer);
    }

    @Override
    public long countProducedNonToxicBottles(int producerId) {
        Producer producer = producerRepository.findOne(producerId);

        if (producer == null) {
            throw new EntityNotFoundException(producerId);
        }

        return bottleRepository.countNotToxicByProducer(producer);
    }


    @Override
    @Transactional
    public void deleteProducer(int producerId) {
        try {
            producerRepository.delete(producerId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(producerId);
        }
    }

    @Override
    @Transactional
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
