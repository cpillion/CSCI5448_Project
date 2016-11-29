package com.csci5448.pages.user_pages;

import com.csci5448.content.News;
import com.csci5448.pages.Page;

public class NewsPage extends Page {

    public static String READ_ARTICLE_ID = "read_article";

    public NewsPage() {
        super.addPageAction(READ_ARTICLE_ID, this::readArticleAction);
    }

    private void readArticleAction(News news) {

    }

    public void displayPage() {
        //TODO
    }

}
