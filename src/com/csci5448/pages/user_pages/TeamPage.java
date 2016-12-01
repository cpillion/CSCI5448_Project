package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class TeamPage extends Page {

    public static final String SELECT_PLAYER_ID = "select_player";
    public static final String ADD_FAVORITE_TEAM_ID = "add_favorite_team";
    public static final String VIEW_PLAYERS_ID = "view_players";

    private final Team team;
    private final List<Player> players;

    public TeamPage(Team team) {
        this.team = team;
        super.addPageAction(ADD_FAVORITE_TEAM_ID, this::addFavoriteTeam);
        super.addPageAction(VIEW_PLAYERS_ID, this::viewPlayersAction);
        super.addPageAction(SELECT_PLAYER_ID, this::selectPlayerAction);

        try (Session session = Controller.sessionFactory.openSession()) {
            Query<Player> players = session.createQuery("FROM Player WHERE team_id=" + team.getId(), Player.class);
            this.players = players.list();
        }
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
        for (Player player : players) {
            System.out.println("\t" + player.getName());
        }
        System.out.println("\nPlease type \'" + SELECT_PLAYER_ID + " <player>\' to view information about a specific player.");
    }

    private void selectPlayerAction(String playerName) {
        List<Player> filteredList = players.stream().filter(player ->
                player.getName().equalsIgnoreCase(playerName)).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            return;
        }

        Controller.setCurrentPage(new PlayerPage(filteredList.get(0)));
    }

    public void displayPage() {
        System.out.println(team + "\n");
        System.out.println("To add this team to your list of favorites, type \'" + ADD_FAVORITE_TEAM_ID + "\'.");
        System.out.println("To view a list of players on this team, type \'" + VIEW_PLAYERS_ID + "\'.");
    }

}