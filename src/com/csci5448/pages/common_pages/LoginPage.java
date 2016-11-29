package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.Credentials;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.JournalistLobbyPage;
import org.hibernate.Session;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class LoginPage extends Page {

    public static final String USER_LOGIN_ID = "user_login";
    public static final String JOURNALIST_LOGIN_ID = "journalist_login";
    public static final String SIGNUP_ID = "signup";

    public LoginPage() {
        super.addPageAction(USER_LOGIN_ID, this::userLoginAction);
        super.addPageAction(JOURNALIST_LOGIN_ID, this::journalistLoginAction);
        super.addPageAction(SIGNUP_ID, o -> Controller.setCurrentPage(new CreateAccountPage()));
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
        System.out.println("Attempting to log in as a journalist...");

        JournalistAccount journalistAccount = login(new JournalistAccount(credentials[0], credentials[1], false, false),
                JournalistAccount.class);

        if (journalistAccount == null) {
            System.out.println("Username or password incorrect.");
            return;
        }

        System.out.println("Login successful. You are now being taken to the Journalist Lobby. "+
                "You may logout at any time by typing \'logout\'.");
        Controller.setCurrentAccount(journalistAccount);
        Controller.setCurrentPage(new JournalistLobbyPage());
    }

    private <T extends Account> T login(T account, Class<T> clazz) {
        if (account.getUsername().length() == 0 || account.getPassword().length() == 0) {
            return null;
        }

        try (Session session = Controller.sessionFactory.openSession()) {
            T existingAccount = session.get(clazz, account.getUsername());
            if (existingAccount == null) {
                return null;
            }

            if (!account.getPassword().equals(existingAccount.getPassword())) {
                return null;
            }

            return existingAccount;
        }
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
