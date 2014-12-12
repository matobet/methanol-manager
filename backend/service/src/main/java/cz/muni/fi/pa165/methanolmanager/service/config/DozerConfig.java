package cz.muni.fi.pa165.methanolmanager.service.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer.xml"));

        return mapper;
    }
}
