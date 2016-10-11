package com.csci5448.pages.journalist_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.journalist_page_actions.WriteArticleAction;

public class JournalistLobbyPage extends Page {

    public static String WRITE_ARTICLE_ID = "write_article";

    public JournalistLobbyPage() {
        super.addPageAction(new WriteArticleAction(WRITE_ARTICLE_ID));
    }

    public void displayPage() {
        //TODO
    }

}
