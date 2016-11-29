package com.csci5448.accounts;

import javax.persistence.*;

@MappedSuperclass
public abstract class Account {

    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean activated;

    public Account() {}

    public Account(String username, String password, boolean activated) {
        this.username = username;
        this.password = password;
        this.activated = activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
