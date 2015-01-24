package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zuzana on 1/23/2015.
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class User extends AbstractPersistable<Integer> {

    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    private String password;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
