package cz.muni.fi.pa165.methanolmanager.api.make;

import cz.muni.fi.pa165.methanolmanager.service.MakeService;
import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zuzana on 1/23/2015.
 */

@RestController
@RequestMapping("/api/makes")
public class MakeController {

    @Inject
    MakeService makeService;

    @RequestMapping("/{id}")
    public MakeDto getMake(@PathVariable int id) {
        return makeService.getMake(id);
    }

    @RequestMapping(method = GET)
    public List<MakeDto> getMakes() {
        return makeService.getMakes();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MakeDto createMake(@RequestBody MakeDto make) {
        return makeService.createMake(make);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteMake(@PathVariable int id) {
        makeService.deleteMake(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public MakeDto updateMake(@PathVariable("id") int id, @RequestBody MakeDto make) {
        return makeService.updateMake(make);
    }
}