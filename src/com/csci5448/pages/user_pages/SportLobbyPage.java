package com.csci5448.pages.user_pages;

import com.csci5448.accounts.Account;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import java.util.*;

public class SportLobbyPage extends Page {

    public static final String VIEW_NEWS_ID = "view_news";
    public static final String VIEW_LEAGUE_ID = "view_league";
    public static final String VIEW_TEAMS_ID = "view_teams";
    public static final String VIEW_FAVORITE_TEAMS_ID = "view_favorite_teams";
    public static final String VIEW_FAVORITE_PLAYERS_ID = "view_favorite_players";

    public SportLobbyPage() {
        super.addPageAction(VIEW_NEWS_ID, this::viewNewsAction);
        super.addPageAction(VIEW_LEAGUE_ID, this::viewLeagueAction);
        super.addPageAction(VIEW_TEAMS_ID, this::viewTeamsAction);
        super.addPageAction(VIEW_FAVORITE_TEAMS_ID, this::viewFavoriteTeamsAction);
        super.addPageAction(VIEW_FAVORITE_PLAYERS_ID, this::viewFavoritePlayersAction);
    }

    private void viewNewsAction(Sport sport) {
        //Controller.setCurrentPage(new NewsArticlePage());
    }

    private void viewLeagueAction(Sport sport) {

    }

    private void viewTeamsAction(Sport sport) {

    }

    private void viewFavoriteTeamsAction(Account account) {

    }

    private void viewFavoritePlayersAction(Account account) {

    }

    public void displayPage() {
        String[] navPages = {"View News", "View Leagues", "View Teams", "View Favorite Teams", "View Favorite Players"};
        Map content = new HashMap();
        content.put(navPages[0], VIEW_NEWS_ID);
        content.put(navPages[1], VIEW_LEAGUE_ID);
        content.put(navPages[2], VIEW_TEAMS_ID);
        content.put(navPages[3], VIEW_FAVORITE_TEAMS_ID);
        content.put(navPages[4], VIEW_FAVORITE_PLAYERS_ID);
        System.out.println("Welcome to the ESP-NGen Lobby Page!\n\n" +
                            "Please select an option from the menu below by typing it into your console:");
        for (int i = 0; i < navPages.length; i++) {
            System.out.println("   " + navPages[i]);
        }
    }

}
