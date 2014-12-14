package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;

public interface MakeService {
    void createMake(MakeDto makeDto);

    boolean hasToxicBottles(int makeId);

    void deleteMake(int makeId);

    MakeDto updateMake(MakeDto makeDto);
}
