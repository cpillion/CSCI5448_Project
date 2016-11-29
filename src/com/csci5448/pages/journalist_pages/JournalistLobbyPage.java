package com.csci5448.pages.journalist_pages;

import com.csci5448.pages.Page;

public class JournalistLobbyPage extends Page {

    public static String WRITE_ARTICLE_ID = "write_article";

    public JournalistLobbyPage() {
        super.addPageAction(WRITE_ARTICLE_ID, this::writeArticleAction);
    }

    private void writeArticleAction(Object o) {

    }

    public void displayPage() {
        //TODO
    }

}
