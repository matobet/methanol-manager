package cz.muni.fi.pa165.methanolmanager;

import cz.muni.fi.pa165.methanolmanager.repository.BottleRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by pavel on 12.10.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class ProducerTest {

    @Inject
    BottleRepository bottleRepository;
}
