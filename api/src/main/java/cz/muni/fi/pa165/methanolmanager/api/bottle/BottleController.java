package cz.muni.fi.pa165.methanolmanager.api.bottle;

import cz.muni.fi.pa165.methanolmanager.service.BottleService;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/bottles")
public class BottleController {
    @Inject
    BottleService bottleService;

    @RequestMapping("/{id}")
    public BottleDto getBottle(@PathVariable int id) {
        return bottleService.getBottle(id);
    }

    @RequestMapping(method = GET)
    public List<BottleDto> getBottles() {
        return bottleService.getBottles();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BottleDto createBottle(@RequestBody BottleDto bottle) {
        return bottleService.createBottle(bottle);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteBottle(@PathVariable int id) {
        bottleService.deleteBottle(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public BottleDto updateBottle(@PathVariable("id") int id, @RequestBody BottleDto bottle) {
        return bottleService.updateBottle(bottle);
    }

    @RequestMapping(value = "/{id}/stamp", method = POST)
    public void stampBottle(@PathVariable("id") int id) {
        bottleService.stampBottle(id);
    }
}