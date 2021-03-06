package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Martin Betak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BottleDto {

    private Integer id;

    private String name;

    private boolean toxic;

    private Date productionDate;

    private Date stampDate;

    private String makeName;

    private String storeName;
}
