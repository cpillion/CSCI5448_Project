package com.csci5448.pages.user_pages;

import com.csci5448.content.Player;
import com.csci5448.pages.Page;

public class TeamPage extends Page {

    public static final String SELECT_PLAYER_ID = "select_player";
    public static final String ADD_FAVORITE_PLAYER_ID = "add_favorite_player";

    public TeamPage() {
        super.addPageAction(SELECT_PLAYER_ID, this::selectPlayerAction);
        super.addPageAction(ADD_FAVORITE_PLAYER_ID, this::addFavoritePlayerAction);
    }

    private void selectPlayerAction(Player player) {

    }

    private void addFavoritePlayerAction(Player player) {

    }

    public void displayPage() {
        //TODO
    }

}