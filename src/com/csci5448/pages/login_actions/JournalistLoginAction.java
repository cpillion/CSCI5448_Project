package com.csci5448.pages.login_actions;

import com.csci5448.accounts.Credentials;
import com.csci5448.control.Controller;
import com.csci5448.pages.PageAction;

public class JournalistLoginAction extends PageAction<Credentials> {

    public JournalistLoginAction(String identifier) {
        super(identifier);
    }

    public void performAction(Credentials credentials) {
        boolean verified = Controller.journalistAccountDAO.verifyPassword(credentials.getUsername(),
                credentials.getPassword());
        if (verified) {
            //TODO
        } else {
            //TODO
        }
    }

}
