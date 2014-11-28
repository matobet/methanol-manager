package cz.muni.fi.pa165.methanolmanager.api.producer;

import cz.muni.fi.pa165.methanolmanager.service.ProducerService;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

    @Inject
    ProducerService producerService;

    @Inject
    Mapper mapper;

    @RequestMapping("/{id}")
    public ProducerDto getProducer(@PathVariable int id) {
        return producerService.getProducer(id);
    }

    @RequestMapping(method = GET)
    public List<ProducerDto> getProducers() {
        return producerService.getProducers();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerDto createProducer(@RequestBody ProducerDto producer) {
        return producerService.createProducer(producer);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteProducer(@PathVariable int id) {
        producerService.deleteProducer(id);
    }
}
