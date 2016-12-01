package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

public class TeamPage extends Page {

    private final Team team;
    public static final String SELECT_PLAYER_ID = "select_player";
    public static final String ADD_FAVORITE_TEAM_ID = "add_favorite_team";
    public static final String VIEW_PLAYERS_ID = "view_players";

    public TeamPage(Team team) {
        this.team = team;
        super.addPageAction(ADD_FAVORITE_TEAM_ID, this::addFavoriteTeam);
    }

    private void addFavoriteTeam(Object o) {
        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);
        try (Session session = Controller.sessionFactory.openSession()) {
            userAccount.addFavoriteTeam(team);
            if (!SessionManager.getSessionManager().performOp(session, session::update, userAccount)) {
                return;
            }
        }
        System.out.println("The " + team.getName() + " have been added to your list of favorite teams!");
    }

    public void displayPage() {
        System.out.println("Welcome to the " + team.getName() + " page!");
        System.out.println("To add this team to your list of favorites, type \'" + ADD_FAVORITE_TEAM_ID + "\'.");
        System.out.println("To view a list of players on this team, type \'" + VIEW_PLAYERS_ID + "\'.");
    }

}