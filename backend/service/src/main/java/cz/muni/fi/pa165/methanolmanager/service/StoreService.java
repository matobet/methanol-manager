package cz.muni.fi.pa165.methanolmanager.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;

/**
 * @author Martin Betak
 */
@Service
@Transactional(readOnly = true)
public class StoreService {

    @Inject
    StoreRepository storeRepository;

    @Inject
    Mapper mapper;

    public List<StoreDto> getStores() {
        List<StoreDto> stores = new ArrayList<>();
        for (Store store : storeRepository.findAll()) {
            stores.add(mapper.map(store, StoreDto.class));
        }
        return stores;
    }

    public StoreWithBottlesDto getStoreWithBottles(int storeId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new EntityNotFoundException(storeId);
        }

        return mapper.map(store, StoreWithBottlesDto.class);
    }

    @Transactional
    public void createStore(StoreDto storeDto) {
        Store store = mapper.map(storeDto, Store.class);

        storeRepository.save(store);
    }

    @Transactional
    public void deleteStore(int storeId) {
        try {
            storeRepository.delete(storeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(storeId);
        }
    }
}
