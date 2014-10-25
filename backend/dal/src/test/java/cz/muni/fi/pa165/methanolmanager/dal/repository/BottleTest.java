package cz.muni.fi.pa165.methanolmanager.dal.repository;

import cz.muni.fi.pa165.methanolmanager.dal.DalConfig;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
 * @author Martin Betak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DalConfig.class)
@Transactional
public class BottleTest {

    @Inject
    BottleRepository bottleRepository;

    @Inject
    MakeRepository makeRepository;

    @Inject
    ProducerRepository producerRepository;

    @Inject
    StoreRepository storeRepository;

    private Producer bozkovProducer;
    private Producer spisProducer;

    private Make bozkov;
    private Make spis;

    private Store brnenka;
    private Store tesco;
    
    @Before
    public void setup() {
        setupProducers();
        setupMakes();
        setupStores();
    }

    private void setupProducers() {
        bozkovProducer = new Producer();
        bozkovProducer.setName("Bozkov s.r.o");

        spisProducer = new Producer();
        spisProducer.setName("Spis s.r.o");

        producerRepository.save(bozkovProducer);
        producerRepository.save(spisProducer);
    }

    private void setupMakes() {
        bozkov = new Make();
        bozkov.setName("Bozkov");
        bozkov.setProducer(bozkovProducer);

        spis = new Make();
        spis.setName("Spis");
        spis.setProducer(spisProducer);

        makeRepository.save(bozkov);
        makeRepository.save(spis);
    }

    private void setupStores() {
        brnenka = new Store();
        brnenka.setName("Brnenka");

        tesco = new Store();
        tesco.setName("Tesco");

        storeRepository.save(brnenka);
        storeRepository.save(tesco);
    }

    @Test
    public void testCreateBottle() {
        Bottle bottle = new Bottle();
        bottle.setName("foo");

        bottleRepository.save(bottle);

        assertThat(bottleRepository.findAll(), is(Arrays.asList(bottle)));
    }

    @Test
    public void testDeleteBottle() {
        Bottle bottle = new Bottle();
        bottle.setStampDate(new Date(System.currentTimeMillis() - 360000));
        bottle.setName("Vodka");

        Bottle bottle2 = new Bottle();
        bottle2.setStampDate(new Date(System.currentTimeMillis() - 360000));
        bottle2.setName("Bozkov");

        Bottle bottle3 = new Bottle();
        bottle3.setName("Becher");

        bottleRepository.save(bottle);
        bottleRepository.save(bottle2);
        bottleRepository.save(bottle3);

        Bottle deleteThisBottle = bottleRepository.findByName("Bozkov");

        bottleRepository.delete(deleteThisBottle);

        assertThat(bottleRepository.findByName("Bozkov"), nullValue());
    }

    @Test
    public void testFindToxic() {
        Bottle bottle = new Bottle();
        bottle.setName("Vodka");

        Bottle bottle2 = new Bottle();
        bottle2.setName("Bozkov");
        bottle2.setToxic(true);

        bottleRepository.save(bottle);
        bottleRepository.save(bottle2);

        assertThat(bottleRepository.findAllToxic(), is(Arrays.asList(bottle2)));
    }

    @Test
    public void testFindStamped() {
        Bottle bottle = new Bottle();
        bottle.setStampDate(new Date(System.currentTimeMillis() - 360000));
        bottle.setName("Vodka");

        Bottle bottle2 = new Bottle();
        bottle2.setStampDate(new Date(System.currentTimeMillis() - 360000));
        bottle2.setName("Bozkov");

        Bottle bottle3 = new Bottle();
        bottle3.setName("Becher");

        bottleRepository.save(bottle);
        bottleRepository.save(bottle2);
        bottleRepository.save(bottle3);

        assertThat(bottleRepository.findAllStamped().size(), equalTo(2));
    }

    @Test
    public void testCountToxic() {
        Bottle bottle = new Bottle();
        bottle.setName("Vodka");
        bottle.setMake(bozkov);
        bottle.setStore(brnenka);

        Bottle bottle2 = new Bottle();
        bottle2.setName("Tuzemak");
        bottle2.setToxic(true);
        bottle2.setMake(bozkov);
        bottle2.setStore(brnenka);

        Bottle bottle3 = new Bottle();
        bottle3.setName("Slivka");
        bottle3.setToxic(true);
        bottle3.setMake(spis);
        bottle3.setStore(tesco);

        Bottle bottle4 = new Bottle();
        bottle4.setName("Ceresna");
        bottle4.setMake(spis);
        bottle3.setStore(tesco);

        bottleRepository.save(bottle);
        bottleRepository.save(bottle2);
        bottleRepository.save(bottle3);

        assertThat(bottleRepository.countToxicByMake(bozkov), is(equalTo(1l)));
        assertThat(bottleRepository.countToxicByMake(spis), is(equalTo(1l)));

        assertThat(bottleRepository.countToxicByProducer(bozkovProducer), is(equalTo(1l)));
        assertThat(bottleRepository.countToxicByProducer(spisProducer), is(equalTo(1l)));

        assertThat(bottleRepository.countToxicByStore(brnenka), is(equalTo(1l)));
        assertThat(bottleRepository.countToxicByStore(tesco), is(equalTo(1l)));
    }
}
