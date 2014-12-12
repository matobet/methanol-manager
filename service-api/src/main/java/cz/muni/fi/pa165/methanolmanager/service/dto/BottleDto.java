package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Martin Betak
 */
@Data
public class BottleDto {

    private Integer id;

    private String name;

    private boolean toxic;

    private Date productionDate;

    private Date stampDate;

    private String makeName;
}
