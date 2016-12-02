package com.csci5448.control;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.accounts.UserAccount;
import com.csci5448.data.JournalistAccountDAO;
import com.csci5448.data.SportDAO;
import com.csci5448.data.UserAccountDAO;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.WelcomePage;
import com.csci5448.pages.journalist_pages.JournalistLobbyPage;
import com.csci5448.pages.user_pages.UserLobbyPage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Stack;

public class Controller {

    public static final JournalistAccountDAO journalistAccountDAO = new JournalistAccountDAO();
    public static final UserAccountDAO userAccountDAO = new UserAccountDAO();
    public static final SportDAO sportDAO = new SportDAO();

    public static SessionFactory sessionFactory;

    private static Stack<Page> previousPages = new Stack<>();
    private static Page currentPage;
    private static Account currentAccount;

    public static void initSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static void setCurrentPage(Page page) {
        if (currentPage != null) {
            previousPages.push(currentPage);
        }
        currentPage = page;
        currentPage.displayPage();
    }

    public static void returnToPreviousPage() {
        if (previousPages.size() == 0) {
            return;
        }
        Page previousPage = previousPages.pop();
        if (previousPage != null) {
            currentPage = previousPage;
            currentPage.displayPage();
        }
    }

    public static void logout() {
        if (currentAccount == null) {
            return;
        }

        System.out.println("Logging out...");
        currentAccount = null;
        currentPage = null;
        previousPages = new Stack<>();
        setCurrentPage(new WelcomePage());
    }

    public static void goToLobbyPage() {
        if (currentAccount == null || !currentAccount.isActivated()) {
            return;
        }
        if (currentAccount instanceof UserAccount) {
            Controller.setCurrentPage(new UserLobbyPage());
        } else if (currentAccount instanceof JournalistAccount) {
            Controller.setCurrentPage(new JournalistLobbyPage());
        }
    }

    public static void sendCommandToPage(String command, Object arg) {
        try {
            currentPage.performAction(command.toLowerCase(), arg);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    public static <T extends Account> T getCurrentAccount(Class<T> clazz) {
        return clazz.cast(currentAccount);
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

}
