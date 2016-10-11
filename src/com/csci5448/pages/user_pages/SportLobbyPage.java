package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.*;

public class SportLobbyPage extends Page {

    public static final String VIEW_NEWS_ID = "view_news";
    public static final String VIEW_LEAGUE_ID = "view_league";
    public static final String VIEW_TEAMS_ID = "view_teams";
    public static final String VIEW_FAVORITE_TEAMS_ID = "view_favorite_teams";
    public static final String VIEW_FAVORITE_PLAYERS_ID = "view_favorite_players";

    public SportLobbyPage() {
        super.addPageAction(new ViewNewsAction(VIEW_NEWS_ID));
        super.addPageAction(new ViewLeagueAction(VIEW_LEAGUE_ID));
        super.addPageAction(new ViewTeamsAction(VIEW_TEAMS_ID));
        super.addPageAction(new ViewFavoriteTeamsAction(VIEW_FAVORITE_TEAMS_ID));
        super.addPageAction(new ViewFavoritePlayersAction(VIEW_FAVORITE_PLAYERS_ID));
    }

    public void displayPage() {
        //TODO
    }

}
