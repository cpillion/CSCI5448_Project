package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.accounts.UserAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.JournalistLobbyPage;
import com.csci5448.pages.user_pages.SportLobbyPage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreateAccountPage extends Page {

    private static final String CREATE_USER_ACCOUNT_ID = "create_user_account";
    private static final String CREATE_JOURNALIST_ACCOUNT_ID = "create_journalist_account";

    public CreateAccountPage() {
        super.addPageAction(CREATE_USER_ACCOUNT_ID, this::createUserAccount);
        super.addPageAction(CREATE_JOURNALIST_ACCOUNT_ID, this::createJournalistAccount);
    }

    private void createUserAccount(String[] credentials) {
        UserAccount userAccount = new UserAccount(credentials[0], credentials[1], false);
        if (!saveAccount(userAccount, UserAccount.class)) {
            return;
        }

        System.out.println("Signup successfull! You are now being taken to the Sport Lobby Page.");
        Controller.setCurrentAccount(userAccount);
        Controller.setCurrentPage(new SportLobbyPage());
    }

    private void createJournalistAccount(String[] credentials) {
        JournalistAccount journalistAccount = new JournalistAccount(credentials[0], credentials[1], false);
        if (!saveAccount(journalistAccount, JournalistAccount.class)) {
            return;
        }

        System.out.println("Signup successfull! You are now being taken to the Journalist Lobby Page.");
        Controller.setCurrentAccount(journalistAccount);
        Controller.setCurrentPage(new JournalistLobbyPage());
    }

    private <T extends Account> boolean saveAccount(T account, Class<T> clazz) {
        if (account.getUsername().length() == 0 || account.getPassword().length() == 0) {
            return false;
        }

        try (Session session = Controller.sessionFactory.openSession()) {
            T existingAccount = session.get(clazz, account.getUsername());
            if (existingAccount != null) {
                System.out.println("The username \'" + account.getUsername() + "\' is already in use.");
                return false;
            }

            Transaction transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public void displayPage() {
        System.out.println("Welcome to the account creation page!");
        System.out.println("Please type \'" + CREATE_USER_ACCOUNT_ID + " <username> <password> to create a"
                + " user account,\n or \'" + CREATE_JOURNALIST_ACCOUNT_ID + " <username> <password> to create a"
                + " journalist account");
    }
}
