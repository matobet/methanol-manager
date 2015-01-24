package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface BottleService {

    List<BottleDto> getBottles();

    BottleDto getBottle(int bottleId);

    @Secured({"ROLE_ADMIN"})
    BottleDto createBottle(BottleDto bottleDto);

    @Secured({"ROLE_ADMIN"})
    void deleteBottle(int bottleId);

    @Secured({"ROLE_ADMIN"})
    void stampBottle(int bottleId);

    @Secured({"ROLE_ADMIN"})
    BottleDto updateBottle(BottleDto storeDto);
}
