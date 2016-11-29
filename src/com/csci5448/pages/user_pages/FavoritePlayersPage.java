package com.csci5448.pages.user_pages;

import com.csci5448.content.Player;
import com.csci5448.pages.Page;

import java.util.Map;
import java.util.function.Consumer;

public class FavoritePlayersPage extends Page {

    public static String DELETE_FAVORITE_PLAYER_ID = "delete_favorite_player";

    private Map<String, Consumer> actionMap;

    public FavoritePlayersPage() {
        super.addPageAction(DELETE_FAVORITE_PLAYER_ID, this::deleteFavoritePlayerAction);
    }

    private void deleteFavoritePlayerAction(Player player) {
        String s = player.getTeam();
        s.hashCode();
    }

    public void displayPage() {
        //TODO
    }

}
