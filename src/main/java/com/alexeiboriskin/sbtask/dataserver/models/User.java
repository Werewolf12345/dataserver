package com.alexeiboriskin.sbtask.dataserver.models;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@DynamicUpdate
public class User {

    private static final PasswordEncoder PASSWORD_ENCODER =
            new BCryptPasswordEncoder();

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean passEncoded;
    private String password;
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

    public boolean isPassEncoded() {
        return passEncoded;
    }

    public void setPassEncoded(boolean passEncoded) {
        this.passEncoded = passEncoded;
    }

    @Column(name = "PASSWORD", nullable = false, updatable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            if (!isPassEncoded()) {
                this.password = PASSWORD_ENCODER.encode(password);
                setPassEncoded(true);
            } else {
                this.password = password;
            }
        }
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
