package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.dozer.Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Betak
 */
@Service
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService {

    @Inject
    StoreRepository storeRepository;

    @Inject
    Mapper mapper;

    @Override
    public List<StoreDto> getStores() {
        List<StoreDto> stores = new ArrayList<>();
        for (Store store : storeRepository.findAll()) {
            stores.add(mapper.map(store, StoreDto.class));
        }
        return stores;
    }

    @Override
    public StoreDto getStore(int storeId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new EntityNotFoundException(storeId);
        }

        return mapper.map(store, StoreDto.class);
    }

    @Override
    public StoreWithBottlesDto getStoreWithBottles(int storeId) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new EntityNotFoundException(storeId);
        }

        return mapper.map(store, StoreWithBottlesDto.class);
    }

    @Override
    @Transactional

    public StoreDto createStore(StoreDto storeDto) {
        Store store = mapper.map(storeDto, Store.class);

        storeRepository.save(store);

        return mapper.map(store, StoreDto.class);
    }

    @Override
    @Transactional

    public void deleteStore(int storeId) {
        try {
            storeRepository.delete(storeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(storeId);
        }
    }

    @Override
    @Transactional

    public StoreDto updateStore(StoreDto storeDto) {
        try {
            Store store = storeRepository.findOne(storeDto.getId());
            mapper.map(storeDto, store);
            storeRepository.save(store);

            return mapper.map(store, StoreDto.class);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(storeDto.getId());
        }
    }
}
