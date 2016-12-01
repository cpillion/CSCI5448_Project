package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.content.Sport;
import com.csci5448.content.Team;
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
        super.addPageAction(VIEW_TEAMS_ID, o -> Controller.setCurrentPage(new ViewTeamsPage(sport)));

        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);

        FavoritesPage<Team> favoriteTeamPage = new FavoritesPage<>(sport, userAccount, userAccount.getFavoriteTeams(),
                userAccount::addFavoriteTeam, userAccount::removeFavoriteTeam, team -> new TeamPage(team));

        FavoritesPage<Player> favoritePlayerPage = new FavoritesPage<>(sport, userAccount,
                userAccount.getFavoritePlayers(), userAccount::addFavoritePlayer, userAccount::removeFavoritePlayer,
                player -> new PlayerPage(player));

        super.addPageAction(VIEW_FAVORITE_TEAMS_ID, o -> Controller.setCurrentPage(favoriteTeamPage));
        super.addPageAction(VIEW_FAVORITE_PLAYERS_ID, o -> Controller.setCurrentPage(favoritePlayerPage));
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
