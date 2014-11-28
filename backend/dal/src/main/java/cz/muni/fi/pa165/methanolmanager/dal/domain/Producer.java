package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import lombok.NoArgsConstructor;

/*
 * @author Zuzana Melsova
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Producer extends AbstractNamedEntity {

    @OneToMany(mappedBy = "producer")
    private List<Make> makes;

    public Producer(String name) {
        super(name);
    }
}
