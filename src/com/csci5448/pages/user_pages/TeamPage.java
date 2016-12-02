package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import org.hibernate.Session;

public class TeamPage extends ViewCollectionsPage<Player> {

    private static final String ADD_FAVORITE_TEAM_ID = "add_favorite_team";
    private static final String VIEW_PLAYERS_ID = "view_players";

    private final Team team;

    public TeamPage(Team team) {
        super(session -> session.createQuery("FROM Player WHERE team=" + team.getId(), Player.class),
                Player::getName, PlayerPage::new);

        this.team = team;
        super.addPageAction(ADD_FAVORITE_TEAM_ID, this::addFavoriteTeam);
        super.addPageAction(VIEW_PLAYERS_ID, this::viewPlayersAction);
    }

    private void addFavoriteTeam(Object o) {
        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);

        try (Session session = Controller.sessionFactory.openSession()) {
            userAccount.addFavoriteTeam(team);
            if (!SessionManager.getSessionManager().performOp(session, session::update, userAccount)) {
                userAccount.removeFavoriteTeam(team);
                return;
            }
        }
        System.out.println("The " + team.getName() + " have been added to your list of favorite teams!");
    }

    private void viewPlayersAction(Object o) {
        System.out.println("The current players on the " + team.getName() + " are:");
        System.out.println(super.toString());
        System.out.println("\nIf you wish to view more information about a specific player, " +
                "please enter that player's name.");
    }

    public void displayPage() {
        System.out.println(team + "\n");
        System.out.println("To add this team to your list of favorites, type \'" + ADD_FAVORITE_TEAM_ID + "\'.");
        System.out.println("To view a list of players on this team, type \'" + VIEW_PLAYERS_ID + "\'.");
    }

}