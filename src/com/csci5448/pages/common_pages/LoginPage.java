package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.accounts.UserAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.JournalistLobbyPage;
import com.csci5448.pages.user_pages.UserLobbyPage;
import org.hibernate.Session;

public class LoginPage extends Page {

    private static final String USER_LOGIN_ID = "user_login";
    private static final String JOURNALIST_LOGIN_ID = "journalist_login";
    private static final String SIGNUP_ID = "signup";
    private static final String VERIFY_EMAIL_ID = "verify";
    private static final String SUPPORT_REQUEST_ID = "request_support";

    public LoginPage() {
        super.addPageActionStringArr(USER_LOGIN_ID, this::userLoginAction);
        super.addPageActionStringArr(JOURNALIST_LOGIN_ID, this::journalistLoginAction);
        super.addPageAction(SIGNUP_ID, arg -> Controller.setCurrentPage(new CreateAccountPage()));
    }

    private void userLoginAction(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }
        System.out.println("  Attempting to log in as " + credentials[0] + "...");

        UserAccount userAccount = login(new UserAccount(credentials[0], credentials[1], false),
                UserAccount.class);

        if (userAccount == null) {
            System.out.println("  Username or password incorrect.");
            return;
        }

        System.out.println("  Login successful. You may logout at any time by typing \'" + Page.LOGOUT_ID + "\'.");
        Controller.setCurrentAccount(userAccount);
        checkEmailVerification(userAccount, new UserLobbyPage());
    }

    private void journalistLoginAction(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }
        System.out.println("  Attempting to log in as a journalist...");

        JournalistAccount journalistAccount = login(new JournalistAccount(credentials[0], credentials[1], false, false),
                JournalistAccount.class);

        if (journalistAccount == null) {
            System.out.println("  Username or password incorrect.");
            return;
        }

        System.out.println("  Login successful. You may logout at any time by typing \'logout\'.");
        Controller.setCurrentAccount(journalistAccount);
        checkEmailVerification(journalistAccount, new JournalistLobbyPage());
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

    private void checkEmailVerification(Account account, Page lobbyPage) {
        if (account.isActivated()) {
            Controller.setCurrentPage(lobbyPage);
            return;
        }

        System.out.println("You have not yet verified your email address.");
        System.out.println("Type \'" + VERIFY_EMAIL_ID + "\' to verify your email address.");
        System.out.println("If you need assistance from an administrator, please type \'" + SUPPORT_REQUEST_ID + "\'.");
        super.addPageAction(SUPPORT_REQUEST_ID, arg -> Controller.setCurrentPage(new SupportRequestPage()));
        super.addPageAction(VERIFY_EMAIL_ID, arg -> {
            Controller.setCurrentPage(new EmailVerificationPage(Controller.getCurrentAccount(), lobbyPage));
            Controller.sendCommandToPage(EmailVerificationPage.RESEND_EMAIL_ID, null);
        });
    }

    public void displayPage() {
        System.out.println(
                "Welcome to the login page!\n" +
                "Please type \'" + USER_LOGIN_ID + " <username> <password>\' if you are a user, or\n" +
                "\'" + JOURNALIST_LOGIN_ID + " <username> <password>\' if you are a journalist.\n" +
                "If you do not yet have an account, please type \'" + SIGNUP_ID + "\'."
        );
    }

}
