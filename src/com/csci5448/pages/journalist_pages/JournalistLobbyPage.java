package com.csci5448.pages.journalist_pages;

import com.csci5448.accounts.JournalistAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.DeleteAccountPage;

public class JournalistLobbyPage extends Page {

    private static String WRITE_ARTICLE_ID = "write_article";
    private static final String DELETE_ACCOUNT_ID = "delete_account";

    public JournalistLobbyPage() {
        super.addPageAction(WRITE_ARTICLE_ID, this::writeArticleAction);
        super.addPageAction(DELETE_ACCOUNT_ID, o -> Controller.setCurrentPage(
                new DeleteAccountPage<>(Controller.getCurrentAccount(JournalistAccount.class))));
    }

    private void writeArticleAction(Object o) {
        JournalistAccount currentAccount = Controller.getCurrentAccount(JournalistAccount.class);
        if (!currentAccount.isProfessionVerified()) {
            System.out.println("Your profession has not yet been verified. Please contact an administrator to " +
                    "verify your account.");
            return;
        }
        Controller.setCurrentPage(new WriteArticlePage());
    }

    public void displayPage() {
        System.out.println("Welcome to the Journalist Lobby Page!\n" +
                            "Please type \"" + WRITE_ARTICLE_ID + "\" to write a news article.");
        System.out.println("If you wish to delete your account, please type \'" + DELETE_ACCOUNT_ID + "\'.");
    }

}
