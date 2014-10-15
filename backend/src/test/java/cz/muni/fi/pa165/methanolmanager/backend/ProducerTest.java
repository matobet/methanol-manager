package cz.muni.fi.pa165.methanolmanager.backend;

import cz.muni.fi.pa165.methanolmanager.backend.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.backend.repository.ProducerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/*
 * @author Zuzana Melsova
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BackendConfig.class)
@Transactional
public class ProducerTest {

    @Inject
    ProducerRepository producerRepository;

    @Test
    public void testCreateProducer() {
        Producer bozkovProducer = new Producer();
        bozkovProducer.setName("bozkovProducer");

        Producer becherProducer = new Producer();
        becherProducer.setName("becherProducer");

        Producer finlandiaProducer = new Producer();
        finlandiaProducer.setName("finlandiaProducer");

        producerRepository.save(bozkovProducer);
        producerRepository.save(becherProducer);
        producerRepository.save(finlandiaProducer);

        assertThat(producerRepository.findAll().size(), equalTo(3));

    }

    @Test
    public void testDeleteProducer() {
        Producer bozkovProducer = new Producer();
        bozkovProducer.setName("bozkovProducer");

        Producer becherProducer = new Producer();
        becherProducer.setName("becherProducer");

        Producer finlandiaProducer = new Producer();
        finlandiaProducer.setName("finlandiaProducer");

        producerRepository.save(bozkovProducer);
        producerRepository.save(becherProducer);
        producerRepository.save(finlandiaProducer);

        assertThat(producerRepository.findAll().size(), equalTo(3));

        Producer deleteThisProducer = producerRepository.findByName("becherProducer");

        producerRepository.delete(deleteThisProducer);

        assertThat(producerRepository.findAll().size(), equalTo(2));
        assertThat(producerRepository.findByName("becherProducer"), nullValue());
    }
}
