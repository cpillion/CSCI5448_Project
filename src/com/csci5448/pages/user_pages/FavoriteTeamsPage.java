package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteTeamsPage extends Page {

    public static final String DELETE_FAVORITE_TEAM = "delete";
    public static final String VIEW_FAVORITE_TEAM = "view";

    private final List<Team> favoriteTeams;
    private final UserAccount currentAccount;

    public FavoriteTeamsPage(Sport sport) {
        super.addPageAction(DELETE_FAVORITE_TEAM, this::deleteFavoriteTeamAction);
        super.addPageAction(VIEW_FAVORITE_TEAM, this::viewFavoriteTeamAction);

        currentAccount = Controller.getCurrentAccount(UserAccount.class);
        favoriteTeams = currentAccount.getFavoriteTeams().stream().filter(team ->
                team.getSport() == sport).collect(Collectors.toList());
    }

    private void deleteFavoriteTeamAction(String teamName) {
        Team team = getTeam(teamName);
        if (team == null) {
            return;
        }

        currentAccount.removeFavoriteTeam(team);
        try (Session session = Controller.sessionFactory.openSession()) {
            if (!SessionManager.getSessionManager().performOp(session, session::update, currentAccount)) {
                return;
            }
        }
        System.out.println(team.getName() + " has been removed from your favorites.\n\n");
        favoriteTeams.remove(team);
        displayPage();
    }

    private void viewFavoriteTeamAction(String teamName) {
        Team team = getTeam(teamName);
        if (team == null) {
            return;
        }

        Controller.setCurrentPage(new TeamPage(team));
    }

    private Team getTeam(String teamName) {
        List<Team> filteredList = favoriteTeams.stream().filter(team ->
                team.getName().equalsIgnoreCase(teamName)).collect(Collectors.toList());

        if (filteredList.size() == 0) {
            return null;
        }

        return filteredList.get(0);
    }

    public void displayPage() {
        System.out.println("Your favorite teams are:");
        for (Team team : favoriteTeams) {
            System.out.println("\t" + team.getName());
        }
        System.out.println("\nType \'" + DELETE_FAVORITE_TEAM + " <team>\' to delete a team from your favorites,\n" +
                "or type \'" + VIEW_FAVORITE_TEAM + " <team>\' to view more information about a specific team.");
    }

}
