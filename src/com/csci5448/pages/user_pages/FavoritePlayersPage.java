package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.DeleteFavoritePlayerAction;

public class FavoritePlayersPage extends Page {

    public static String DELETE_FAVORITE_PLAYER_ID = "delete_favorite_player";

    public FavoritePlayersPage() {
        super.addPageAction(new DeleteFavoritePlayerAction(DELETE_FAVORITE_PLAYER_ID));
    }

    public void displayPage() {
        //TODO
    }

}
