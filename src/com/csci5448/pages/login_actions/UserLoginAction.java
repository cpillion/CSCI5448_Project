package com.csci5448.pages.login_actions;

import com.csci5448.accounts.Credentials;
import com.csci5448.control.Controller;
import com.csci5448.pages.PageAction;

public class UserLoginAction extends PageAction<Credentials> {

    public UserLoginAction(String identifier) {
        super(identifier);
    }

    public void performAction(Credentials credentials) {
        System.out.println("logging in as a user with\n\tusername: " + credentials.getUsername() +
                "\n\tpassword: " + credentials.getPassword());
        /*boolean verified = Controller.userAccountDAO.verifyPassword(credentials.getUsername(),
                credentials.getPassword());
        if (verified) {
            //TODO
        } else {
            //TODO
        }*/
    }

}
