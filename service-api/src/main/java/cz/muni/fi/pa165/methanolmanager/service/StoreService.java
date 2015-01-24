package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface StoreService {
    List<StoreDto> getStores();

    StoreDto getStore(int storeId);

    StoreWithBottlesDto getStoreWithBottles(int storeId);

    @Secured({"ROLE_ADMIN"})
    StoreDto createStore(StoreDto storeDto);

    @Secured({"ROLE_ADMIN"})
    void deleteStore(int storeId);

    @Secured({"ROLE_ADMIN"})
    StoreDto updateStore(StoreDto storeDto);
}
