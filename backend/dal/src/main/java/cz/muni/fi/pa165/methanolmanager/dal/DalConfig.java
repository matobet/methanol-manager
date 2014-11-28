/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.muni.fi.pa165.methanolmanager.dal;

import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@EntityScan(basePackages = "cz.muni.fi.pa165.methanolmanager.dal.domain")
@ComponentScan
public class DalConfig {
    @Bean
    InitializingBean seedData(final StoreRepository storeRepository) {
        return new InitializingBean() {
            @Override
            public void afterPropertiesSet() throws Exception {
                storeRepository.save(new Store("Brnenka", "Lidicka 234"));
                storeRepository.save(new Store("Tesco", "Kr. Pole"));
                storeRepository.save(new Store("NonStop", "Arabska 15"));
            }
        };
    }
}
