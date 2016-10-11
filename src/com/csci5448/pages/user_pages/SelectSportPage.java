package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.SelectSportAction;

public class SelectSportPage extends Page {

    public static final String SELECT_SPORT_ID = "select_sport";

    public SelectSportPage() {
        super.addPageAction(new SelectSportAction(SELECT_SPORT_ID));
    }

    public void displayPage() {
        //TODO
    }

}
