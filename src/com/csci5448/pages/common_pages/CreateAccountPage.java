package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.accounts.UserAccount;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.JournalistLobbyPage;
import com.csci5448.pages.user_pages.UserLobbyPage;
import org.hibernate.Session;

public class CreateAccountPage extends Page {

    private static final String CREATE_USER_ACCOUNT_ID = "create_user_account";
    private static final String CREATE_JOURNALIST_ACCOUNT_ID = "create_journalist_account";

    public CreateAccountPage() {
        super.addPageAction(CREATE_USER_ACCOUNT_ID, this::createUserAccount);
        super.addPageAction(CREATE_JOURNALIST_ACCOUNT_ID, this::createJournalistAccount);
    }

    private void createUserAccount(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }

        UserAccount userAccount = new UserAccount(credentials[0], credentials[1], false);
        if (!saveAccount(userAccount, UserAccount.class)) {
            return;
        }

        Controller.setCurrentPage(new EmailVerificationPage(userAccount, new UserLobbyPage()));
        Controller.sendCommandToPage(EmailVerificationPage.RESEND_EMAIL_ID, null);
    }

    private void createJournalistAccount(String[] credentials) {
        if (credentials == null || credentials.length != 2) {
            return;
        }

        JournalistAccount journalistAccount = new JournalistAccount(credentials[0], credentials[1], false, false);
        if (!saveAccount(journalistAccount, JournalistAccount.class)) {
            return;
        }

        Controller.setCurrentPage(new EmailVerificationPage(journalistAccount, new JournalistLobbyPage()));
        Controller.sendCommandToPage(EmailVerificationPage.RESEND_EMAIL_ID, null);
    }

    private <T extends Account> boolean saveAccount(T account, Class<T> clazz) {
        if (account.getUsername().length() == 0 || account.getPassword().length() == 0) {
            return false;
        }

        if (!EmailControl.getEmailControl().isEmailValid(account.getUsername())) {
            System.out.println(account.getUsername() + " is not a valid email address.");
            return false;
        }

        try (Session session = Controller.sessionFactory.openSession()) {
            T existingAccount = session.get(clazz, account.getUsername());
            if (existingAccount != null) {
                System.out.println("The username \'" + account.getUsername() + "\' is already in use.");
                return false;
            }

            return SessionManager.getSessionManager().performOp(session, session::save, account);
        }
    }

    @Override
    public void displayPage() {
        System.out.println("Welcome to the account creation page!");
        System.out.println("Please type \'" + CREATE_USER_ACCOUNT_ID + " <username> <password> to create a"
                + " user account,\n or \'" + CREATE_JOURNALIST_ACCOUNT_ID + " <username> <password> to create a"
                + " journalist account");
        System.out.println("Also, please note that your username must be a valid email address.");
    }



}
