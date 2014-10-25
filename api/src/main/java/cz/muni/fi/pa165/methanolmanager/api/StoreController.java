package cz.muni.fi.pa165.methanolmanager.api;

import cz.muni.fi.pa165.methanolmanager.service.StoreService;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Inject
    StoreService storeService;

    @RequestMapping("/{id}")
    public StoreWithBottlesDto getStore(@PathVariable int id) {
        return storeService.getStoreWithBottles(id);
    }
}
