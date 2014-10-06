package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Producer extends AbstractNamedEntity {

    @OneToMany(mappedBy = "producer")
    private List<Bottle> bottles;
}
