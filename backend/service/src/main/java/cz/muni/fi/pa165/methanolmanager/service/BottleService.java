package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Pavel Vomacka
 */

@Service
@Transactional(readOnly = true)
public class BottleService {

    @Inject
    BottleRepository bottleRepository;

    @Inject
    Mapper mapper;

    @Transactional
    public void createBottle(BottleDto bottleDto) {
        Bottle bottle = mapper.map(bottleDto, Bottle.class);
        bottleRepository.save(bottle);
    }

    @Transactional
    public void deleteBottle(int bottleId) {
        try {
            bottleRepository.delete(bottleId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(bottleId);
        }
    }

    @Transactional
    public void stampBottle(int bottleId) {
        Bottle bottle = bottleRepository.findOne(bottleId);

        if(bottle == null) {
            throw new EntityNotFoundException(bottleId);
        }

        if (!bottle.isStamped()) {
            bottle.setStampDate(new Date(System.currentTimeMillis()));
        }

        bottleRepository.save(bottle);
    }
}
