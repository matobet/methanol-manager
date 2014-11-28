package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Martin Betak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private Integer id;

    private String name;

    private String address;
}
