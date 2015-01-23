package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zuzana Melsova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeDto {

    private Integer id;

    private String name;

    private String producerName;
}
