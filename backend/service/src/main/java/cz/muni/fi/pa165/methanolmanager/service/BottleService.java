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
 * Created by pavel on 27.10.14.
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

    /*
     * If bottle is already stamped function returns false.
     * If stamped is set then function returns true.
     */
    @Transactional
    public boolean setStamped(int bottleId) {
        Bottle bottle;
        try {
            bottle = bottleRepository.findOne(bottleId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(bottleId);
        }
        if(!bottle.isStamped()) {
            bottle.setStampDate(new Date(System.currentTimeMillis()));
        } else {
            return false;
        }

        bottleRepository.save(bottle);
        return true;
    }

    /*
     * Returns true if bottle is stamped.
     */
    public boolean isStamped(int bottleId) {
        Bottle bottle;
        try {
            bottle = bottleRepository.findOne(bottleId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(bottleId);
        }

        if(bottle.isStamped()) {
            return true;
        } else {
            return false;
        }

    }

}
