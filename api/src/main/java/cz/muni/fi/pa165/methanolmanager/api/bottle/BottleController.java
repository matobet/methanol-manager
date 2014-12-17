package cz.muni.fi.pa165.methanolmanager.api.bottle;

import cz.muni.fi.pa165.methanolmanager.service.BottleService;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/bottles")
public class BottleController {
    @Inject
    BottleService bottleService;

    @RequestMapping("/{id}")
    public BottleDto getBottle(@PathVariable int id){
        return bottleService.getBottle(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BottleDto> getBottles(){
        return bottleService.getBottles();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BottleDto createBottle(@RequestBody BottleDto bottleDto){
        return bottleService.createBottle(bottleDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBottle(@PathVariable int id) {
        bottleService.deleteBottle(id);
    }
}