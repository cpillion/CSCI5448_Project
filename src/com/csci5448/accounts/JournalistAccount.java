package com.csci5448.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "journalists")
public class JournalistAccount extends Account {

    public JournalistAccount(String username, String password, boolean activated) {
        super(username, password, activated);
    }

}
