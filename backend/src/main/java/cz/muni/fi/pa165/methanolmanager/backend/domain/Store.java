package cz.muni.fi.pa165.methanolmanager.backend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/*
 * @author Petr Barton
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Store extends AbstractNamedEntity {

    @OneToMany(mappedBy = "store")
    private List<Bottle> bottles;
}
