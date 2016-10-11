package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.ReadArticleAction;

public class NewsPage extends Page {

    public static String READ_ARTICLE_ID = "read_article";

    public NewsPage() {
        super.addPageAction(new ReadArticleAction(READ_ARTICLE_ID));
    }

    public void displayPage() {
        //TODO
    }

}
