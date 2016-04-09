package pers.evan.fastrepair.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Evan on 2016/4/9.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
