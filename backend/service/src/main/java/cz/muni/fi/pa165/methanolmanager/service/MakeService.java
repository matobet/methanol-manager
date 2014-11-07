package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Zuzana Melsova
 */

@Service
@Transactional(readOnly = true)
public class MakeService {

    @Inject
    MakeRepository makeRepository;

    @Inject
    BottleRepository bottleRepository;

    @Inject
    Mapper mapper;

    @Transactional
    public void createMake(MakeDto makeDto){
        Make make = mapper.map(makeDto, Make.class);
        makeRepository.save(make);
    }

    public boolean hasToxicBottles(int makeId){
        Make make = makeRepository.findOne(makeId);
        if (make == null) {
            throw new EntityNotFoundException(makeId);
        }
        return (bottleRepository.countToxicByMake(make) > 0);
    }

    @Transactional
    public void deleteMake(int makeId){
        try {
            makeRepository.delete(makeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(makeId);
        }
    }
}
