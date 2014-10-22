package cz.muni.fi.pa165.methanolmanager.repository;

import cz.muni.fi.pa165.methanolmanager.App;
import cz.muni.fi.pa165.methanolmanager.domain.Store;
import cz.muni.fi.pa165.methanolmanager.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
 * @author Pavel Vomacka
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class StoreTest {

    @Inject
    StoreRepository storeRepository;

    @Test
    public void testCreateStore() {
        Store storeNamesti = new Store();
        storeNamesti.setName("Na namesti");

        Store storeHodiny = new Store();
        storeHodiny.setName("Pod hodinami");

        Store storeTesco = new Store();
        storeTesco.setName("Tesco");

        storeRepository.save(storeNamesti);
        storeRepository.save(storeHodiny);
        storeRepository.save(storeTesco);

        assertThat(storeRepository.findAll().size(), equalTo(3));
        assertThat(storeRepository.findByName("Tesco"), equalTo(storeTesco));
    }

    @Test
    public void testDeleteStore() {
        Store storeNamesti = new Store();
        storeNamesti.setName("Na namesti");

        Store storeHodiny = new Store();
        storeHodiny.setName("Pod hodinami");

        Store storeTesco = new Store();
        storeTesco.setName("Tesco");

        storeRepository.save(storeNamesti);
        storeRepository.save(storeHodiny);
        storeRepository.save(storeTesco);

        assertThat(storeRepository.findAll().size(), equalTo(3));
        assertThat(storeRepository.findByName("Tesco"), equalTo(storeTesco));

        Store deleteThisStore = storeRepository.findByName("Tesco");

        storeRepository.delete(deleteThisStore);

        assertThat(storeRepository.findAll().size(), equalTo(2));
        assertThat(storeRepository.findByName("Tesco"), nullValue());
    }
}
