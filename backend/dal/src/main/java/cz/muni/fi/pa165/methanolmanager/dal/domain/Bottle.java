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
        toxic = checkToxicity();

        if (!toxic)
            stampDate = new Date();
    }

    private boolean checkToxicity(){
        // huge number of chemical tests and tasting by volunteers to find out whether content of this bottle is toxic
        return Math.random() > 0.5;
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
