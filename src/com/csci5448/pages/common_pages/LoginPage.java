package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Credentials;
import com.csci5448.pages.Page;

public class LoginPage extends Page {

    public static final String USER_LOGIN_ID = "user_login";
    public static final String JOURNALIST_LOGIN_ID = "journalist_login";

    public LoginPage() {
        super.addPageAction(USER_LOGIN_ID, this::userLoginAction);
        super.addPageAction(JOURNALIST_LOGIN_ID, this::journalistLoginAction);
    }

    private void userLoginAction(Credentials credentials) {
        System.out.println("logging in as a user with\n\tusername: " + credentials.getUsername() +
                "\n\tpassword: " + credentials.getPassword());
    }

    private void journalistLoginAction(Credentials credentials) {

    }

    public void displayPage() {
        System.out.println("You are on the login page!");
        //TODO Either graphically display this, or do it better in cli.
    }

}
