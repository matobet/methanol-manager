package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.RoleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class providing mock repositories for service Spring test context.
 *
 * @author Martin Betak
 */
@Configuration
@ComponentScan
public class ServiceTestConfig {
    @Bean
    BottleRepository bottleRepository() {
        return Mockito.mock(BottleRepository.class);
    }

    @Bean
    MakeRepository makeRepository() {
        return Mockito.mock(MakeRepository.class);
    }

    @Bean
    ProducerRepository producerRepository() {
        return Mockito.mock(ProducerRepository.class);
    }

    @Bean
    StoreRepository storeRepository() {
        return Mockito.mock(StoreRepository.class);
    }

    @Bean
    RoleRepository roleRepository() {
        return Mockito.mock(RoleRepository.class);
    }

    @Bean
    UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }
}
