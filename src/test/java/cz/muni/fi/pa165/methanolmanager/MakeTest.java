package cz.muni.fi.pa165.methanolmanager;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.domain.Make;
import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class MakeTest {

    @Inject
    BottleRepository bottleRepository;

    @Test
    public void testCreateMake() {
        Make makeBozkov = new Make();
        makeBozkov.setName("Bozkov");

        Bottle bottle1 = new Bottle();
        bottle1.setName("Tuzemsky");
        bottle1.setMake(makeBozkov);

        bottleRepository.save(bottle1);

       //nejakej assert a proste to nejde :D 
    }

}
