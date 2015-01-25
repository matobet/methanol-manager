package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/*
 * @author Pavel Vomacka
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
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
        if (!toxic)
            stampDate = new Date();
    }

    public Bottle(String name, Date productionDate, Date stampDate, boolean toxic, Make make, Store store) {
        super(name);
        this.productionDate = productionDate;
        this.stampDate = stampDate;
        this.toxic = toxic;
        this.make = make;
        this.store = store;
    }
}
