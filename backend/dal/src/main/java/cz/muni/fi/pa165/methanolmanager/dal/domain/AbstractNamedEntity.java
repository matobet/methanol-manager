package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractPersistable<Integer> {

    @NotNull
    private String name;
}
