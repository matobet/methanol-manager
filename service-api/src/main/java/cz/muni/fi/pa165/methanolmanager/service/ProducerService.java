package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;

import java.util.List;

public interface ProducerService {
    ProducerDto createProducer(ProducerDto producerDto);

    ProducerDto getProducer(int producerId);

    List<ProducerDto> getProducers();

    long countProducedToxicBottles(int producerId);

    long countProducedNonToxicBottles(int producerId);

    void deleteProducer(int producerId);

    ProducerDto updateProducer(ProducerDto producerDto);
}
