package com.csci5448.pages.journalist_pages;

import com.csci5448.content.News;
import com.csci5448.pages.Page;

public class WriteArticlePage extends Page {

    public static final String SUBMIT_ARTICLE_ID = "submit_article";

    public WriteArticlePage() {
        super.addPageAction(SUBMIT_ARTICLE_ID, this::submitArticleAction);
    }

    private void submitArticleAction(News news) {

    }

    public void displayPage() {
        //TODO
    }

}
