package com.csci5448.pages.journalist_pages;

import com.csci5448.accounts.JournalistAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.LobbyPage;

public class JournalistLobbyPage extends LobbyPage {

    private static String WRITE_ARTICLE_ID = "write_article";

    public JournalistLobbyPage() {
        super(JournalistAccount.class);
        super.addPageAction(WRITE_ARTICLE_ID, this::writeArticleAction);
    }

    private void writeArticleAction(String arg) {
        JournalistAccount currentAccount = Controller.getCurrentAccount(JournalistAccount.class);
        if (!currentAccount.isProfessionVerified()) {
            System.out.println("Your profession has not yet been verified. Please contact an administrator to " +
                    "verify your account.");
            return;
        }
        Controller.setCurrentPage(new WriteArticlePage());
    }

    public void displayPage() {
        System.out.println("At any time while browsing through the pages, you may type \'" + Page.PREVIOUS_PAGE_ID +
                "\' to return to the previously visited page.");
        System.out.println("Welcome to the Journalist Lobby Page!\n" +
                            "Please type \"" + WRITE_ARTICLE_ID + "\" to write a news article.");
        super.displayPage();
    }

}
