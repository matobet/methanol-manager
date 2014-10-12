package cz.muni.fi.pa165.methanolmanager;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.domain.Make;
import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.repository.MakeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class MakeTest {

    @Inject
    BottleRepository bottleRepository;

    @Inject
    MakeRepository makeRepository;

    @Test
    public void testCreateMake() {
        Make makeBozkov = new Make();
        makeBozkov.setName("Bozkov");

        Bottle bottle1 = new Bottle();
        bottle1.setName("Tuzemsky");
        bottle1.setMake(makeBozkov);

        makeRepository.save(makeBozkov);
        bottleRepository.save(bottle1);

        assertEquals(bottleRepository.findAll().get(0).getMake(), makeBozkov);
    }

}
