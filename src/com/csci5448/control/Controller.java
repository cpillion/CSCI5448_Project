package com.csci5448.control;

import com.csci5448.accounts.Account;
import com.csci5448.content.Sport;
import com.csci5448.data.JournalistAccountDAO;
import com.csci5448.data.SportDAO;
import com.csci5448.data.UserAccountDAO;
import com.csci5448.pages.Page;

import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    public static final JournalistAccountDAO journalistAccountDAO = new JournalistAccountDAO();
    public static final UserAccountDAO userAccountDAO = new UserAccountDAO();
    public static final SportDAO sportDAO = new SportDAO();

    private static Queue<Page> previousPages = new LinkedList<>();
    private static Page currentPage;
    private static Sport currentSport;
    private static Account currentAccount;

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static void setCurrentPage(Page page) {
        if (currentPage != null) {
            previousPages.add(currentPage);
        }
        currentPage = page;
        currentPage.displayPage();
    }

    public static void returnToPreviousPage() {
        if (previousPages.size() == 0) {
            return;
        }
        Page previousPage = previousPages.poll();
        if (previousPage != null) {
            currentPage = previousPage;
            currentPage.displayPage();
        }
    }

    public static Sport getCurrentSport() {
        return currentSport;
    }

    public static void setCurrentSport(Sport sport) {
        currentSport = sport;
    }

    public static void logout() {
        //TODO
        //currentSport = null;
        //currentAccount = null;
        //previousPages.clear();
        //setCurrentPage(new LoginPage());
    }

    public static void sendCommandToPage(String command, Object arg) {
        currentPage.performAction(command, arg);
    }

}
