package cz.muni.fi.pa165.methanolmanager.api.store;

import cz.muni.fi.pa165.methanolmanager.service.StoreService;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreWithBottlesDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Inject
    StoreService storeService;

    @RequestMapping("/{id}")
    public StoreDto getStore(@PathVariable int id) {
        return storeService.getStore(id);
    }

    @RequestMapping("/{id}")
    StoreWithBottlesDto getStoreWithBottles(@PathVariable int id){ return storeService.getStoreWithBottles(id); }

    @RequestMapping(method = GET)
    public List<StoreDto> getStores() {
        return storeService.getStores();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public StoreDto createStore(@RequestBody StoreDto store) {
        return storeService.createStore(store);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public StoreDto updateStore(@PathVariable("id") int id, @RequestBody StoreDto store) {
        return storeService.updateStore(store);
    }
}
