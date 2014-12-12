package cz.muni.fi.pa165.methanolmanager.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Martin Betak
 */
public class StoreServiceTest extends ServiceTest {

    public static final int STORE_ID = 0;
    public static final int INVALID_STORE_ID = 1;
    public static final String STORE_NAME = "tesco";
    public static final String STORE_ADDRESS = "kralovo pole";

    @Inject
    StoreRepository storeRepository;

    @Inject
    MakeRepository makeRepository;

    @Inject
    StoreService storeService;

    @Before
    public void setup() {
        Make make = new Make();
        make.setName("Bozkov");

        Bottle bottle = new Bottle();
        bottle.setToxic(true);
        bottle.setName("vodka");
        bottle.setMake(make);

        Store store = new Store() {{
            setId(STORE_ID);
        }};
        store.setName(STORE_NAME);
        store.setAddress(STORE_ADDRESS);
        store.setBottles(Arrays.asList(bottle));

        bottle.setStore(store);

        when(storeRepository.findOne(STORE_ID)).thenReturn(store);
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
        doThrow(EmptyResultDataAccessException.class).when(storeRepository).delete(INVALID_STORE_ID);
    }

    @Test
    public void testGetStores() {
        assertEquals(STORE_NAME, storeService.getStores().get(0).getName());
    }

    @Test
    public void testGetStoreWithBottles() {
        List<BottleDto> bottles = storeService.getStoreWithBottles(STORE_ID).getBottles();
        assertTrue(bottles.get(0).isToxic());
        assertEquals("vodka", bottles.get(0).getName());
        assertEquals("Bozkov", bottles.get(0).getMakeName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetStoreWithBottles_NotFound() {
        storeService.getStoreWithBottles(INVALID_STORE_ID);
    }

    @Test
    public void testCreateStore() {
        StoreDto storeDto = new StoreDto();
        storeDto.setName(STORE_NAME);
        storeDto.setAddress(STORE_ADDRESS);

        storeService.createStore(storeDto);

        ArgumentCaptor<Store> captor = ArgumentCaptor.forClass(Store.class);
        verify(storeRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), STORE_NAME);
        assertEquals(captor.getValue().getAddress(), STORE_ADDRESS);
    }

    @Test
    public void testDeleteStore() {
        storeService.deleteStore(STORE_ID);

        verify(storeRepository).delete(STORE_ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteStore_NotFound() {
        storeService.deleteStore(INVALID_STORE_ID);
    }
}
