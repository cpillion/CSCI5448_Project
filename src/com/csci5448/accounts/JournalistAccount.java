package com.csci5448.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "journalists")
public class JournalistAccount extends Account {

    @Column
    private boolean professionVerified;

    public JournalistAccount() {}

    public JournalistAccount(String username, String password, boolean activated, boolean professionVerified) {
        super(username, password, activated);
        this.professionVerified = professionVerified;
    }

    public void setProfessionVerified(boolean professionVerified) {
        this.professionVerified = professionVerified;
    }

    public boolean isProfessionVerified() {
        return professionVerified;
    }

}
