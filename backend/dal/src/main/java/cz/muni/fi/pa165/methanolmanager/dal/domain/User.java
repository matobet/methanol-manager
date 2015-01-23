package cz.muni.fi.pa165.methanolmanager.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Created by zuzana on 1/23/2015.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class User extends AbstractNamedEntity{
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
