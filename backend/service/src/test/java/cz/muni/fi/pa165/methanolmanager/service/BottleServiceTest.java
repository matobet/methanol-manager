package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by pavel on 28.10.14.
 */
public class BottleServiceTest extends ServiceTest {

    @Inject
    BottleRepository bottleRepository;

    @Inject
    BottleService bottleService;

    @Before
    public void setup() {
        System.out.println("Ahoj");
    }

    @Test
    public void createBottleTest() {
        BottleDto bottleDto = new BottleDto();
        bottleDto.setName("Vodka");
        bottleDto.setId(0);

        bottleService.createBottle(bottleDto);

        ArgumentCaptor<Bottle> captor = ArgumentCaptor.forClass(Bottle.class);
        verify(bottleRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), "Vodka");

    }
}
