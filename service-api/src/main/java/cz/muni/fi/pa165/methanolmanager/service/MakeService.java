package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;


import java.util.List;

public interface MakeService {
    MakeDto createMake(MakeDto makeDto);

    MakeDto getMake(int makeId);

    List<MakeDto> getMakes();

    boolean hasToxicBottles(int makeId);

    void deleteMake(int makeId);

    MakeDto updateMake(MakeDto makeDto);
}
