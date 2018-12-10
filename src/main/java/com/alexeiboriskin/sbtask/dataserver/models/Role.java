package com.alexeiboriskin.sbtask.dataserver.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@DynamicUpdate
public class Role {

    private long id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}