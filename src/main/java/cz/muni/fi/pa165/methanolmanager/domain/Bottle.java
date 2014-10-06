package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Bottle extends AbstractNamedEntity {

    private boolean toxic;

    private boolean stamped;

    private Date productionDate;

    @ManyToOne
    private Producer producer;

    @ManyToOne
    private Store store;
}
