package cz.muni.fi.pa165.methanolmanager.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Convenient base class for service tests.
 *
 * Enables descendant test classes to {@code @Inject} mocks for repository classes
 * and use them to usual Mockito testing purposes.
 *
 * Example usage:
 * <code>
 *     public class MyServiceTest extends ServiceTest {
 *
 *         @Inject
 *         BottleRepository bottleRepository;    // or some other repo used by MyService
 *
 *         @Inject
 *         MyService myService;
 *
 *         @Before
 *         public void setup() {
 *             when(bottleRepository.someMethodNeededByMyService()).thenReturn(someTestValue);
 *         }
 *
 *         @Test
 *         public void testMyService() {
 *             myService.bottleBusinessMethod();
 *
 *             verify(bottleRepository).someRepositoryMethodExpectedToBeCalledByMyService();
 *         }
 *     }
 * </code>
 *
 * @author Martin Betak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceTestConfig.class)
public abstract class ServiceTest {
}
