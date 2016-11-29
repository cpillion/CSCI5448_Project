package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Credentials;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public class LoginPage extends Page {

    public static final String USER_LOGIN_ID = "user_login";
    public static final String JOURNALIST_LOGIN_ID = "journalist_login";
    public static final String SIGNUP_ID = "signup";

    public LoginPage() {
        super.addPageAction(USER_LOGIN_ID, this::userLoginAction);
        super.addPageAction(JOURNALIST_LOGIN_ID, this::journalistLoginAction);
        super.addPageAction(SIGNUP_ID, this::signupAction);
    }

    private void signupAction(Object o) {
        Controller.setCurrentPage(new CreateAccountPage());
    }

    private void userLoginAction(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }
        System.out.println("Attempting to log in as " + credentials[0] + "...");
    }

    private void journalistLoginAction(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }
        System.out.println("Attempting to log in as a journalist with\n\tusername: " + credentials[0] +
                "\n\tpassword: " + credentials[1]);
    }

    public void displayPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to the login page!\n");
        sb.append("Please type \'" + USER_LOGIN_ID + " <username> <password>\' if you are a user, or\n");
        sb.append("\'" + JOURNALIST_LOGIN_ID + " <username> <password>\' if you are a journalist.\n");
        sb.append("If you do not yet have an account, please type \'" + SIGNUP_ID + "\'.");
        System.out.println(sb.toString());
    }

}
