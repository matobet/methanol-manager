package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/*
 * @author Zuzana Melsova
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Producer extends AbstractNamedEntity {

    @OneToMany(mappedBy = "producer")
    private List<Make> makes;
}
