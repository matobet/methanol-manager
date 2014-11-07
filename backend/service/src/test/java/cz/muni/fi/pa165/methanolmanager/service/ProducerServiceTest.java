package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author Petr Barton
 */
public class ProducerServiceTest extends ServiceTest {

    public static final int PRODUCER_ID = 1;
    public static final int INVALID_PRODUCER_ID = 10;
    public static final String PRODUCER_NAME = "RUDOLF JELÍNEK, a.s.";

    @Inject
    ProducerRepository producerRepository;

    @Inject
    ProducerService producerService;

    @Inject
    BottleRepository bottleRepository;

    @Before
    public void setup() {
        Producer producer = new Producer();
        producer.setName(PRODUCER_NAME);

        when(producerRepository.findOne(PRODUCER_ID)).thenReturn(producer);
        when(producerRepository.findAll()).thenReturn(Arrays.asList(producer));
        when(bottleRepository.countToxicByProducer(producer)).thenReturn(2l);
        when(bottleRepository.countNotToxicByProducer(producer)).thenReturn(1l);
        doThrow(EmptyResultDataAccessException.class).when(producerRepository).delete(INVALID_PRODUCER_ID);
    }

    @Test
    public void testGetProducers() {
        ProducerDto producerDto = producerService.getProducers().get(0);
        assertEquals(PRODUCER_NAME, producerDto.getName());
    }

    @Test
    public void testProducedToxicBottles() {
        assertEquals(2, producerService.countProducedToxicBottles(PRODUCER_ID));
    }

    @Test
    public void testProducedNonToxicBottles() {
        assertEquals(1, producerService.countProducedNonToxicBottles(PRODUCER_ID));
    }

    @Test
    public void testCreateProducer() {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setName(PRODUCER_NAME);

        producerService.createProducer(producerDto);

        ArgumentCaptor<Producer> captor = ArgumentCaptor.forClass(Producer.class);
        verify(producerRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), PRODUCER_NAME);
    }

    @Test
    public void testDeleteProducer() {
        producerService.deleteProducer(PRODUCER_ID);

        verify(producerRepository).delete(PRODUCER_ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteProducer_NotFound() {
        producerService.deleteProducer(INVALID_PRODUCER_ID);
    }
}
