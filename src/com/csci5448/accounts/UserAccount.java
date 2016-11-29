package com.csci5448.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserAccount extends Account {

    public UserAccount() {}

    public UserAccount(String username, String password, boolean activated) {
        super(username, password, activated);
    }

}
