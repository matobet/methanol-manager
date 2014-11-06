package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.Data;
import org.dozer.Mapping;

/**
 * @author Zuzana Melsova
 */
@Data
public class MakeDto {

    private Integer id;

    private String name;

    @Mapping("producer.name")
    private String producerName;
}
