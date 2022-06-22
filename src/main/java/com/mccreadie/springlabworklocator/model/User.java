package com.mccreadie.springlabworklocator.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public enum roleType {Clinician, Receptionist, Admin}

    private String role;
    private String login;


    @Column(length = 68)
    private String password;

    // not saved in the db
    @Transient
    private String confirmPassword;

    private boolean enabled;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = "ROLE_" + role.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
