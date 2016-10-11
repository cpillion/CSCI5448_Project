package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.DeleteFavoriteTeamAction;

public class FavoriteTeamsPage extends Page {

    public static final String DELETE_FAVORITE_TEAM = "delete_favorite_team";

    public FavoriteTeamsPage() {
        super.addPageAction(new DeleteFavoriteTeamAction(DELETE_FAVORITE_TEAM));
    }

    public void displayPage() {
        //TODO
    }

}
