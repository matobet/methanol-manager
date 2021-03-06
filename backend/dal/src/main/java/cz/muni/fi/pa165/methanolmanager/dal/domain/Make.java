package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import lombok.NoArgsConstructor;

/*
 * @author Martin Betak
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Make extends AbstractNamedEntity {

    @ManyToOne
    private Producer producer;

    @OneToMany(mappedBy = "make")
    private List<Bottle> bottles;

    public Make(String name, Producer producer) {
        super(name);
        this.producer = producer;
    }
}
