package com.csci5448.accounts;

public abstract class Account {

    private final String username;
    private boolean activated;

    public Account(String username, boolean activated) {
        this.username = username;
        this.activated = activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getUsername() {
        return username;
    }
}
