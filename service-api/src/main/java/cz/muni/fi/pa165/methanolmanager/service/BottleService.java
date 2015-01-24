package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;

import java.util.List;

public interface BottleService {

    List<BottleDto> getBottles();

    BottleDto getBottle(int bottleId);

    BottleDto createBottle(BottleDto bottleDto);

    void deleteBottle(int bottleId);

    void stampBottle(int bottleId);

    BottleDto updateBottle(BottleDto storeDto);
}
