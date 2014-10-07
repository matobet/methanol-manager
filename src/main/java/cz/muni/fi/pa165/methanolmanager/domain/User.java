package cz.muni.fi.pa165.methanolmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class User extends AbstractNamedEntity {
    public enum Role {
        CUSTOMER,
        POLICEMAN,
        CHEMIST
    }

    @NotNull
    private Role role;
}
