package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Pavel Vomacka
 */

@Service
@Transactional(readOnly = true)
public class BottleServiceImpl implements BottleService {

    @Inject
    BottleRepository bottleRepository;

    @Inject
    Mapper mapper;

    @Override
    public List<BottleDto> getBottles() {
        List<BottleDto> bottles = new ArrayList<>();
        for (Bottle bottle : bottleRepository.findAll()) {
            bottles.add(mapper.map(bottle, BottleDto.class));
        }
        return bottles;
    }

    @Override
    public BottleDto getBottle(int bottleId) {
        Bottle bottle = bottleRepository.findOne(bottleId);
        if (bottle == null) {
            throw new EntityNotFoundException(bottleId);
        }

        return mapper.map(bottle, BottleDto.class);
    }

    @Override
    @Transactional
    public BottleDto createBottle(BottleDto bottleDto) {
        Bottle bottle = mapper.map(bottleDto, Bottle.class);
        bottleRepository.save(bottle);

        return mapper.map(bottle, BottleDto.class);
    }

    @Override
    @Transactional
    public void deleteBottle(int bottleId) {
        try {
            bottleRepository.delete(bottleId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(bottleId);
        }
    }

    @Override
    @Transactional
    public void stampBottle(int bottleId) {
        Bottle bottle = bottleRepository.findOne(bottleId);

        if(bottle == null) {
            throw new EntityNotFoundException(bottleId);
        }

        if (!bottle.isStamped()) {
            bottle.stamp();
        }

        bottleRepository.save(bottle);
    }

    @Override
    @Transactional
    public BottleDto updateBottle(BottleDto storeDto) {
        try {
            Bottle store = bottleRepository.findOne(storeDto.getId());
            mapper.map(storeDto, store);
            bottleRepository.save(store);

            return mapper.map(store, BottleDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(storeDto.getId());
        }
    }
}
