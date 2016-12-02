package com.csci5448.pages.user_pages;

import com.csci5448.content.League;
import com.csci5448.content.Team;

import java.util.stream.Collectors;

public class LeaguePage extends ViewCollectionsPage<Team> {

    private final League league;

    public LeaguePage(League league) {
        super(league.getTeams().stream().collect(Collectors.toList()), Team::getName, TeamPage::new);
        this.league = league;
    }

    public void displayPage() {
        System.out.println("The teams in the " + league.getLeague() + " are:");
        System.out.println(super.toString());
        System.out.println("Please type the name of a team for more information about it.");
    }
}
