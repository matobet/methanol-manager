package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Petr Barton
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerDto {
    public Integer id;
    private String name;
}
