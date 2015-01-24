package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;

import java.util.List;

public interface StoreService {
    List<StoreDto> getStores();

    StoreDto getStore(int storeId);

    StoreWithBottlesDto getStoreWithBottles(int storeId);

    StoreDto createStore(StoreDto storeDto);

    void deleteStore(int storeId);

    StoreDto updateStore(StoreDto storeDto);
}
