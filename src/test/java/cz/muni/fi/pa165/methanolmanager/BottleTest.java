package cz.muni.fi.pa165.methanolmanager;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class BottleTest {

    @Inject
    BottleRepository bottleRepository;

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

        assertThat(bottleRepository.findByToxicTrue(), is(Arrays.asList(bottle2)));
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

}
