package cz.muni.fi.pa165.methanolmanager.service;


import cz.muni.fi.pa165.methanolmanager.App;
import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.domain.Make;
import cz.muni.fi.pa165.methanolmanager.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Martin Betak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class StoreServiceTest {

    @Inject
    StoreRepository storeRepository;

    @Inject
    BottleRepository bottleRepository;

    @Inject
    MakeRepository makeRepository;

    @Inject
    StoreService storeService;

    @Test
    public void testGetStores() {
        Store store = new Store();
        store.setName("tesco");
        storeRepository.save(store);
        assertEquals("tesco", storeService.getStores().get(0).getName());
    }

    @Test
    public void testGetStoreWithBottles() {
        Store store = new Store();
        store.setName("tesco");

        Make make = new Make();
        make.setName("Bozkov");

        Bottle bottle = new Bottle();
        bottle.setToxic(true);
        bottle.setName("vodka");
        bottle.setStore(store);
        bottle.setMake(make);

        storeRepository.save(store);
        makeRepository.save(make);
        bottleRepository.save(bottle);

        List<BottleDto> bottles = storeService.getStoreWithBottles(store.getId()).getBottles();
        assertTrue(bottles.get(0).isToxic());
        assertEquals("vodka", bottles.get(0).getName());
        assertEquals("Bozkov", bottles.get(0).getMakeName());
    }

    @Test
    public void testCreateStore() {
        StoreDto storeDto = new StoreDto();
        storeDto.setName("tesco");
        storeDto.setAddress("kralovo pole");

        storeService.createStore(storeDto);

        Store store = storeRepository.findByName("tesco");
        assertEquals("kralovo pole", store.getAddress());
    }

    @Test
    public void testDeleteStore() {
        Store store = new Store();
        store.setName("tesco");
        store.setAddress("kralovo pole");

        storeRepository.save(store);

        storeService.deleteStore(store.getId());

        assertThat(storeRepository.findAll(), is(empty()));
    }
}
