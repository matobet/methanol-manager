package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ProducerService {

    @Secured({"ROLE_ADMIN"})
    ProducerDto createProducer(ProducerDto producerDto);

    ProducerDto getProducer(int producerId);

    List<ProducerDto> getProducers();

    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    long countProducedToxicBottles(int producerId);

    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    long countProducedNonToxicBottles(int producerId);

    @Secured({"ROLE_ADMIN"})
    void deleteProducer(int producerId);

    @Secured({"ROLE_ADMIN"})
    ProducerDto updateProducer(ProducerDto producerDto);
}
