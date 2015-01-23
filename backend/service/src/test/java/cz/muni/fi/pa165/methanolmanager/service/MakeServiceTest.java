package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.inject.Inject;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Zuzana Melsova
 */
public class MakeServiceTest extends ServiceTest {
    public static final int MAKE_ID = 1;
    public static final int INVALID_MAKE_ID = 0;
    public static final String MAKE_NAME = "Bozkov";
    private static final String PRODUCER_NAME = "Bozkov s.r.o";

    @Inject
    MakeRepository makeRepository;

    @Inject
    BottleRepository bottleRepository;

    @Inject
    ProducerRepository producerRepository;

    @Inject
    MakeService makeService;

    @Before
    public void setup(){

        Make make = new Make();
        make.setName(MAKE_NAME);

        Bottle bottle = new Bottle();
        bottle.setToxic(true);
        bottle.setName("Vodka");
        bottle.setMake(make);

        make.setBottles(Arrays.asList(bottle));

        Producer producer = new Producer();
        producer.setName(PRODUCER_NAME);

        make.setProducer(producer);

        when(makeRepository.findOne(MAKE_ID)).thenReturn(make);
        when(bottleRepository.countToxicByMake(make)).thenReturn((long) 1);
        when(producerRepository.findByName(PRODUCER_NAME)).thenReturn(producer);
        doThrow(EmptyResultDataAccessException.class).when(makeRepository).delete(INVALID_MAKE_ID);
    }



    @Test
    public void testHasToxicBottles(){
        assertTrue(makeService.hasToxicBottles(MAKE_ID));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testHasToxicBottles_NotFound(){
        makeService.hasToxicBottles(INVALID_MAKE_ID);
    }

    @Test
    public void testCreateMake() {
        MakeDto makeDto = new MakeDto();
        makeDto.setName(MAKE_NAME);
        makeDto.setProducerName(PRODUCER_NAME);

        makeService.createMake(makeDto);

        ArgumentCaptor<Make> captor = ArgumentCaptor.forClass(Make.class);
        verify(makeRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), MAKE_NAME);
        assertEquals(captor.getValue().getProducer().getName(), PRODUCER_NAME);
    }

    @Test
    public void testDeleteMake() {
        makeService.deleteMake(MAKE_ID);

        verify(makeRepository).delete(MAKE_ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteMake_NotFound() {
        makeService.deleteMake(INVALID_MAKE_ID);
    }

}
