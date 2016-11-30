package com.csci5448.pages.user_pages;

import com.csci5448.accounts.Account;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public class SportOptionPage extends Page {

    public static final String VIEW_NEWS_ID = "view_news";
    public static final String VIEW_LEAGUE_ID = "view_league";
    public static final String VIEW_TEAMS_ID = "view_teams";
    public static final String VIEW_FAVORITE_TEAMS_ID = "view_favorite_teams";
    public static final String VIEW_FAVORITE_PLAYERS_ID = "view_favorite_players";

    private final Sport sport;

    public SportOptionPage(Sport sport) {
        this.sport = sport;
        super.addPageAction(VIEW_NEWS_ID, o -> Controller.setCurrentPage(new NewsPage(sport)));
        super.addPageAction(VIEW_LEAGUE_ID, o -> Controller.setCurrentPage(new LeaguePage(sport)));
        super.addPageAction(VIEW_TEAMS_ID, o -> Controller.setCurrentPage(new TeamPage(sport)));
        super.addPageAction(VIEW_FAVORITE_TEAMS_ID, this::viewFavoriteTeamsAction);
        super.addPageAction(VIEW_FAVORITE_PLAYERS_ID, this::viewFavoritePlayersAction);
    }

    private void viewFavoriteTeamsAction(Account account) {

    }

    private void viewFavoritePlayersAction(Account account) {

    }

    @Override
    public void displayPage() {
        System.out.println("Welcome to the " + sport + " page.");
        System.out.println("Please select an option from the menu below:");

        String[] pageActions = {VIEW_NEWS_ID, VIEW_LEAGUE_ID, VIEW_TEAMS_ID, VIEW_FAVORITE_TEAMS_ID,
                VIEW_FAVORITE_PLAYERS_ID};
        for (String pageAction : pageActions) {
            System.out.println("\t" + pageAction);
        }
        System.out.print("Selection: ");
    }

}
