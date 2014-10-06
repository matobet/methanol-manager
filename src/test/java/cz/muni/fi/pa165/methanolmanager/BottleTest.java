package cz.muni.fi.pa165.methanolmanager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.methanolmanager.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;

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
}
