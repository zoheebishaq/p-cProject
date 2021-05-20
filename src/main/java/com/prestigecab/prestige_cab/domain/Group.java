package com.prestigecab.prestige_cab.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "GROUPS")
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;
    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
