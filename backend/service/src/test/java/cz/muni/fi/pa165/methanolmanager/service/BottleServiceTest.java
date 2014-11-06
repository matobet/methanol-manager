package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Pavel Vomacka
 */
public class BottleServiceTest extends ServiceTest {
    public static final int BOTTLE_ID = 1;
    public static final int INVALID_BOTTLE_ID = 0;
    public static final String BOTTLE_NAME = "Tuzemsky";

    @Inject
    BottleRepository bottleRepository;

    @Inject
    BottleService bottleService;

    @Before
    public void setup() {
        Bottle bottle = new Bottle();
        bottle.setName(BOTTLE_NAME);

        when(bottleRepository.findOne(BOTTLE_ID)).thenReturn(bottle);
        doThrow(EmptyResultDataAccessException.class).when(bottleRepository).delete(INVALID_BOTTLE_ID);
    }

    @Test
    public void testCreateBottle() {
        BottleDto bottleDto = new BottleDto();
        bottleDto.setName(BOTTLE_NAME);

        bottleService.createBottle(bottleDto);

        ArgumentCaptor<Bottle> captor = ArgumentCaptor.forClass(Bottle.class);
        verify(bottleRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), BOTTLE_NAME);
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
