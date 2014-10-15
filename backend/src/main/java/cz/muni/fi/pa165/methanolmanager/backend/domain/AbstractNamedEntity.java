package cz.muni.fi.pa165.methanolmanager.backend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/*
 * @author Martin Betak
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractPersistable<Integer> {

    @NotNull
    private String name;
}
