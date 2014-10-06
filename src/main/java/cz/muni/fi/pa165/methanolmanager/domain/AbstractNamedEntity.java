package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractNamedEntity extends AbstractPersistable<Integer> {

    @NotNull
    private String name;
}
