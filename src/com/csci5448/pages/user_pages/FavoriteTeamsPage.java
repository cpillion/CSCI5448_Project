package com.csci5448.pages.user_pages;

import com.csci5448.content.Team;
import com.csci5448.pages.Page;

public class FavoriteTeamsPage extends Page {

    public static final String DELETE_FAVORITE_TEAM = "delete_favorite_team";

    public FavoriteTeamsPage() {
        super.addPageAction(DELETE_FAVORITE_TEAM, this::deleteFavoriteTeamAction);
    }

    private void deleteFavoriteTeamAction(Team team) {

    }

    public void displayPage() {
        //TODO
    }

}
