package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
public class Store extends AbstractNamedEntity {

    private String address;

    @OneToMany(mappedBy = "store")
    private List<Bottle> bottles;

    public Store(String name, String address) {
        super(name);
        this.address = address;
    }
}
