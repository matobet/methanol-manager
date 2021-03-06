package cz.muni.fi.pa165.methanolmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.EmptyResultDataAccessException;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;

/**
 * @author Pavel Vomacka
 */
public class BottleServiceTest extends ServiceTest {
    public static final int BOTTLE_ID = 1;
    public static final int INVALID_BOTTLE_ID = 0;
    public static final String BOTTLE_NAME = "Tuzemsky";
    public static final String MAKE_NAME = "Bozkov";
    public static final String STORE_NAME = "Tesco";

    @Inject
    BottleRepository bottleRepository;

    @Inject
    MakeRepository makeRepository;

    @Inject
    StoreRepository storeRepository;

    @Inject
    BottleService bottleService;

    @Before
    public void setup() {
        Make make = new Make();
        make.setName(MAKE_NAME);

        Store store = new Store();
        store.setName(STORE_NAME);

        Bottle bottle = new Bottle();
        bottle.setName(BOTTLE_NAME);
        bottle.setMake(make);
        bottle.setStore(store);

        // reset bottle repository mock before each test
        // in order to reset the argument captor call count
        reset(bottleRepository);
        when(bottleRepository.findOne(BOTTLE_ID)).thenReturn(bottle);
        when(makeRepository.findByName(MAKE_NAME)).thenReturn(make);
        when(storeRepository.findByName(STORE_NAME)).thenReturn(store);
        doThrow(EmptyResultDataAccessException.class).when(bottleRepository).delete(INVALID_BOTTLE_ID);
    }

    @Test
    public void testCreateBottle() {
        BottleDto bottleDto = new BottleDto();
        bottleDto.setName(BOTTLE_NAME);
        bottleDto.setMakeName(MAKE_NAME);
        bottleDto.setStoreName(STORE_NAME);

        bottleService.createBottle(bottleDto);

        ArgumentCaptor<Bottle> captor = ArgumentCaptor.forClass(Bottle.class);
        verify(bottleRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), BOTTLE_NAME);
        assertEquals(captor.getValue().getMake().getName(), MAKE_NAME);
        assertEquals(captor.getValue().getStore().getName(), STORE_NAME);
    }

    @Test
    public void testStampBottle() {
        bottleService.stampBottle(BOTTLE_ID);

        assertTrue(bottleRepository.findOne(BOTTLE_ID).isStamped());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testStampBottle_NotFound() {
        bottleService.stampBottle(INVALID_BOTTLE_ID);
    }

    @Test
    public void testDeleteBottle() {
        bottleService.deleteBottle(BOTTLE_ID);

        verify(bottleRepository).delete(BOTTLE_ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteBottle_NotFound() {
        bottleService.deleteBottle(INVALID_BOTTLE_ID);
    }
}
