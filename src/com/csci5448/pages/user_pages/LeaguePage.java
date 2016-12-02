package com.csci5448.pages.user_pages;

import com.csci5448.content.League;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

import java.util.List;
import java.util.stream.Collectors;

public class LeaguePage extends Page {

    private final League league;

    public LeaguePage(League league) {
        this.league = league;
        for (Team team : league.getTeams()) {
            super.addPageAction(team.getName(), this::selectTeamAction);
        }
    }

    private void selectTeamAction(String teamName) {
        List<Team> filteredTeams = league.getTeams().stream().filter(team ->
                team.getName().equalsIgnoreCase(teamName)).collect(Collectors.toList());

        if (filteredTeams.size() == 0) {
            return;
        }
        Controller.setCurrentPage(new TeamPage(filteredTeams.get(0)));
    }

    public void displayPage() {
        System.out.println("The teams in this league are:");
        for (Team team : league.getTeams()) {
            System.out.println("\t" + team.getName());
        }
        System.out.println("Please type the name of a team for more information about it.");
    }
}
