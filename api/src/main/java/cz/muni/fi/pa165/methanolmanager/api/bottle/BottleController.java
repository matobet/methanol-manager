package cz.muni.fi.pa165.methanolmanager.api.bottle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BottleController {

    @RequestMapping("/api/bottles")
    List<String> getBottleNames() {
        return Arrays.asList("Vodka", "Finlandia", "Bozkov");
    }
}