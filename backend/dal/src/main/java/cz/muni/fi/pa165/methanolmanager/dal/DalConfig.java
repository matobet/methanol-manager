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

import cz.muni.fi.pa165.methanolmanager.dal.domain.Bottle;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Make;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Producer;
import cz.muni.fi.pa165.methanolmanager.dal.domain.Store;
import cz.muni.fi.pa165.methanolmanager.dal.repository.BottleRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.MakeRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.ProducerRepository;
import cz.muni.fi.pa165.methanolmanager.dal.repository.StoreRepository;
import java.util.Date;
import org.springframework.beans.factory.InitializingBean;
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
    InitializingBean seedData(final StoreRepository storeRepository,
            final ProducerRepository producerRepository,
            final MakeRepository makeRepository,
            final BottleRepository bottleRepository) {
        return new InitializingBean() {
            @Override
            public void afterPropertiesSet() throws Exception {
                Store s1 = new Store("Brnenka", "Lidicka 234");
                Store s2 = new Store("Tesco", "Kr. Pole");
                Store s3 = new Store("NonStop", "Arabska 15");

                storeRepository.save(s1);
                storeRepository.save(s2);
                storeRepository.save(s3);

                Producer p1 = new Producer("Bozkov Producer");
                Producer p2 = new Producer("Finlandia Producer");
                Producer p3 = new Producer("Bechcer Producer");

                producerRepository.save(p1);
                producerRepository.save(p2);
                producerRepository.save(p3);

                Make m1 = new Make("Bozkov", p1);
                Make m2 = new Make("Finlandia", p2);
                Make m3 = new Make("Bechcer", p3);

                //makeRepository.save(m1);
                makeRepository.save(m2);
                makeRepository.save(m3);

//                bottleRepository.save(new Bottle("Tuzemak", new Date(), new Date(), false, m1, s1));
//                bottleRepository.save(new Bottle("Vodka", new Date(), new Date(), false, m2, s2));
//                bottleRepository.save(new Bottle("Becherovka", new Date(), new Date(), false, m3, s3));
//                bottleRepository.save(new Bottle("Tuzemak", new Date(), new Date(), false, m1, s1));
//                bottleRepository.save(new Bottle("Vodka", new Date(), new Date(), false, m2, s2));
//                bottleRepository.save(new Bottle("Becherovka", new Date(), new Date(), false, m3, s3));
//                bottleRepository.save(new Bottle("Tuzemak", new Date(), new Date(), false, m1, s1));
//                bottleRepository.save(new Bottle("Vodka", new Date(), new Date(), false, m2, s2));
//                bottleRepository.save(new Bottle("Becherovka", new Date(), new Date(), false, m3, s3));
//                bottleRepository.save(new Bottle("Tuzemak", new Date(), new Date(), false, m1, s1));
//                bottleRepository.save(new Bottle("Vodka", new Date(), new Date(), false, m2, s2));
//                bottleRepository.save(new Bottle("Becherovka", new Date(), new Date(), false, m3, s3));
            }
        };
    }
}
