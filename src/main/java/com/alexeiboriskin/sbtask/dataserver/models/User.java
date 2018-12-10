package com.alexeiboriskin.sbtask.dataserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@DynamicUpdate
public class User {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean passEncoded;
    private Set<Role> roles;

    public User(String username, String firstName, String lastName,
                String email, String password, Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.roles = roles;
    }

    public User() {
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD", nullable = false, updatable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name = "USER_ID"
    )}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}