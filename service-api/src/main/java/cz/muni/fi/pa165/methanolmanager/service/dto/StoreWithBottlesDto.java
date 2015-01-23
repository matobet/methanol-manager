package cz.muni.fi.pa165.methanolmanager.service.dto;

import lombok.*;

import java.util.List;

/**
 * @author Martin Betak
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreWithBottlesDto extends StoreDto {

    private List<BottleDto> bottles;
}
