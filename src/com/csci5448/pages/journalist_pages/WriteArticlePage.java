package com.csci5448.pages.journalist_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.journalist_pages.journalist_page_actions.SubmitArticleAction;

public class WriteArticlePage extends Page {

    public static final String SUBMIT_ARTICLE_ID = "submit_article";

    public WriteArticlePage() {
        super.addPageAction(new SubmitArticleAction(SUBMIT_ARTICLE_ID));
    }

    public void displayPage() {
        //TODO
    }

}
