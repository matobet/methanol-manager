package cz.muni.fi.pa165.methanolmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Martin Betak
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreWithBottlesDto extends StoreDto {

    private List<BottleDto> bottles;
}
