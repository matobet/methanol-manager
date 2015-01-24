package cz.muni.fi.pa165.methanolmanager.service;

import cz.muni.fi.pa165.methanolmanager.service.dto.MakeDto;
import org.springframework.security.access.annotation.Secured;


import java.util.List;

public interface MakeService {

    @Secured({"ROLE_ADMIN"})
    MakeDto createMake(MakeDto makeDto);

    MakeDto getMake(int makeId);

    List<MakeDto> getMakes();

    @Secured({"ROLE_ADMIN", "ROLE_POLICE"})
    boolean hasToxicBottles(int makeId);

    @Secured({"ROLE_ADMIN"})
    void deleteMake(int makeId);

    @Secured({"ROLE_ADMIN"})
    MakeDto updateMake(MakeDto makeDto);
}
