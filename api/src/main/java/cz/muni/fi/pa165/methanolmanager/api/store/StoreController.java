package cz.muni.fi.pa165.methanolmanager.api.store;

import cz.muni.fi.pa165.methanolmanager.service.StoreService;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Inject
    StoreService storeService;

    @Inject
    Mapper mapper;

    @RequestMapping("/{id}")
    public StoreDto getStore(@PathVariable int id) {
        return storeService.getStore(id);
    }

    @RequestMapping(method = POST)
    public void createStore(@RequestBody StoreDto store) {
        storeService.createStore(store);
    }
}