package cz.muni.fi.pa165.methanolmanager;

import cz.muni.fi.pa165.methanolmanager.domain.User;
import cz.muni.fi.pa165.methanolmanager.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Transactional
public class UserTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Franta");
        user.setRole(User.Role.CUSTOMER);

        userRepository.save(user);

        assertThat(userRepository.findAll(), is(Arrays.asList(user)));
    }
}
