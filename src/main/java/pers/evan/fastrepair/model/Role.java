package pers.evan.fastrepair.model;

/**
 * Created by Evan on 2016/4/9.
 */

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Privilege> privileges;

    @ManyToMany
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
