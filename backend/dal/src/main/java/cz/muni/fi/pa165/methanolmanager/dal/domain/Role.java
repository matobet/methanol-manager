package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Role extends AbstractPersistable<Integer> {
    /*public enum UserRole{
        ADMIN, POLICE
    }*/

    @NotNull
    private String name;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private List<User> users;

    public Role(String name){
        this.name = name;
    }
}
