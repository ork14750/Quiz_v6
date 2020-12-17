package com.esiea.webapp_apiaccount_player.model;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "user")
public class User {
    private int id;
    private String username;
    private String password;
    private String passwordConfirm;
    private boolean active;
    private String role;
    private int points;

    public User() {
        this.active = true;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }
}
