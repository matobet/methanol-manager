package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zuzana Melsova
 */

@Service
@Transactional(readOnly = true)
public class MakeServiceImpl implements MakeService {

    @Inject
    MakeRepository makeRepository;

    @Inject
    BottleRepository bottleRepository;

    @Inject
    ProducerRepository producerRepository;

    @Inject
    Mapper mapper;

    @Override
    @Transactional
    public MakeDto createMake(MakeDto makeDto){
        Make make = mapper.map(makeDto, Make.class);
        Producer producer = producerRepository.findByName(makeDto.getProducerName());
        if (producer == null) {
            throw new EntityNotFoundException(makeDto.getProducerName());
        }
        make.setProducer(producer);

        makeRepository.save(make);

        return mapper.map(make, MakeDto.class);
    }

    @Override
    public MakeDto getMake(int makeId) {
        Make make = makeRepository.findOne(makeId);
        if (make == null) {
            throw new EntityNotFoundException(makeId);
        }

        return mapper.map(make, MakeDto.class);
    }

    @Override
    public List<MakeDto> getMakes() {
        List<MakeDto> makes = new ArrayList<>();
        for (Make make : makeRepository.findAll()) {
            makes.add(mapper.map(make, MakeDto.class));
        }
        return makes;
    }

    @Override
    public boolean hasToxicBottles(int makeId){
        Make make = makeRepository.findOne(makeId);
        if (make == null) {
            throw new EntityNotFoundException(makeId);
        }
        return (bottleRepository.countToxicByMake(make) > 0);
    }

    @Override
    @Transactional
    public void deleteMake(int makeId){
        try {
            makeRepository.delete(makeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(makeId);
        }
    }

    @Override
    @Transactional
    public MakeDto updateMake(MakeDto makeDto) {
        try {
            Make make = makeRepository.findOne(makeDto.getId());
            mapper.map(makeDto, make);
            makeRepository.save(make);

            return mapper.map(make, MakeDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(makeDto.getId());
        }
    }
}
