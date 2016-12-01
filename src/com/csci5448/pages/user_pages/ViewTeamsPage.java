package com.csci5448.pages.user_pages;

import com.csci5448.content.Sport;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ViewTeamsPage extends Page {

    public static final String SELECT_TEAM_ID = "select_team";

    private final Sport sport;
    private final List<Team> teams;

    public ViewTeamsPage(Sport sport) {
        this.sport = sport;
        super.addPageAction(SELECT_TEAM_ID, this::selectTeamAction);

        try (Session session = Controller.sessionFactory.openSession()) {
            Query<Team> teams = session.createQuery("FROM Team where sport=" + sport.ordinal(), Team.class);
            this.teams = teams.list();
        }
    }

    private void selectTeamAction(String teamName) {
        List<Team> filteredList = teams.stream().filter(team ->
                team.getName().equalsIgnoreCase(teamName)).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            return;
        }

        Controller.setCurrentPage(new TeamPage(filteredList.get(0)));
    }

    public void displayPage() {
        System.out.println("Welcome to the " + sport + " teams page!");
        System.out.println("The current teams available are: ");
        for (Team team : teams) {
            System.out.println("\t" + team.getName());
        }
        System.out.println("\nPlease type \'" + SELECT_TEAM_ID + " <team>\' to view information about a specific team.");
    }

}
