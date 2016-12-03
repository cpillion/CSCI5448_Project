package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.PageDisplay;

public class SportOptionPage extends Page {

    private static final String VIEW_NEWS_ID = "view_news";
    private static final String VIEW_LEAGUE_ID = "view_leagues";
    private static final String VIEW_TEAMS_ID = "view_teams";
    private static final String VIEW_FAVORITE_TEAMS_ID = "view_favorite_teams";
    private static final String VIEW_FAVORITE_PLAYERS_ID = "view_favorite_players";

    private final Sport sport;

    public SportOptionPage(Sport sport) {
        this.sport = sport;
        super.addPageAction(VIEW_NEWS_ID, arg -> Controller.setCurrentPage(new NewsPage(sport)));
        super.addPageAction(VIEW_LEAGUE_ID, arg -> Controller.setCurrentPage(new ViewLeaguesPage(sport)));
        super.addPageAction(VIEW_TEAMS_ID, arg -> Controller.setCurrentPage(new ViewTeamsPage(sport)));

        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);

        super.addPageAction(VIEW_FAVORITE_TEAMS_ID, arg -> Controller.setCurrentPage(new FavoritesPage<>(sport,
                userAccount, userAccount.getFavoriteTeams(), userAccount::addFavoriteTeam,
                userAccount::removeFavoriteTeam, TeamPage::new)));
        super.addPageAction(VIEW_FAVORITE_PLAYERS_ID, arg -> Controller.setCurrentPage(new FavoritesPage<>(sport,
                userAccount, userAccount.getFavoritePlayers(), userAccount::addFavoritePlayer,
                userAccount::removeFavoritePlayer, PlayerPage::new)));
    }

    @Override
    public void displayPage() {
        PageDisplay.getPageDisplay().showPageWelcomeText(sport.toString());
        PageDisplay.getPageDisplay().showNavCommands();
        System.out.println("\tPlease select an option from the menu below:");

        String[] pageActions = {VIEW_NEWS_ID, VIEW_LEAGUE_ID, VIEW_TEAMS_ID, VIEW_FAVORITE_TEAMS_ID,
                VIEW_FAVORITE_PLAYERS_ID};
        for (String pageAction : pageActions) {
            System.out.println("\t\t" + pageAction);
        }
        PageDisplay.getPageDisplay().showInputPrompt();
    }

}
