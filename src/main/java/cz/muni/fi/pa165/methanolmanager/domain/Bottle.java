package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.dozer.Mapping;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/*
 * @author Pavel Vomacka
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Bottle extends AbstractNamedEntity {

    private boolean toxic;

    private Date productionDate;

    private Date stampDate;

    @ManyToOne
    private Make make;

    @ManyToOne
    private Store store;

    public boolean isStamped() {
        return stampDate != null;
    }

    public void stamp() {
        stampDate = new Date();
    }
}