package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

/**
 * @author Petr Barton
 */
public class ProducerServiceTest extends ServiceTest {

    public static final int PRODUCER_ID = 1;
    public static final String PRODUCER_NAME = "RUDOLF JELÍNEK, a.s.";

    @Inject
    ProducerRepository producerRepository;

    @Inject
    ProducerService producerService;

    @Test
    public void testCreateProducer(){
        ProducerDto producerDto = new ProducerDto();
        producerDto.setName(PRODUCER_NAME);

        producerService.createProducer(producerDto);

        ArgumentCaptor<Producer> captor = ArgumentCaptor.forClass(Producer.class);
        verify(producerRepository).save(captor.capture());

        assertEquals(captor.getValue().getName(), PRODUCER_NAME);
    }

    @Test
    public void testDeleteProducer(){
        producerService.deleteProducer(PRODUCER_ID);

        verify(producerRepository).delete(PRODUCER_ID);
    }
}
