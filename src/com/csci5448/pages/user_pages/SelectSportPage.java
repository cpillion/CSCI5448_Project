package com.csci5448.pages.user_pages;

import com.csci5448.content.Sport;
import com.csci5448.pages.Page;

public class SelectSportPage extends Page {

    public static final String SELECT_SPORT_ID = "select_sport";

    public SelectSportPage() {
        super.addPageAction(SELECT_SPORT_ID, this::selectSportAction);
    }

    private void selectSportAction(Sport sport) {

    }

    public void displayPage() {
        //TODO
    }

}
