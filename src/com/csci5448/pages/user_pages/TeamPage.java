package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.AddFavoritePlayerAction;
import com.csci5448.pages.user_pages.user_page_actions.SelectPlayerAction;

public class TeamPage extends Page {

    public static final String SELECT_PLAYER_ID = "select_player";
    public static final String ADD_FAVORITE_PLAYER_ID = "add_favorite_player";

    public TeamPage() {
        super.addPageAction(new SelectPlayerAction(SELECT_PLAYER_ID));
        super.addPageAction(new AddFavoritePlayerAction(ADD_FAVORITE_PLAYER_ID));
    }

    public void displayPage() {
        //TODO
    }

}