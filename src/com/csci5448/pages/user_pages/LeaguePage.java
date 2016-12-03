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
        makeNewPage(league.getLeague());
        showNavCommands();
        System.out.println("\tThe teams in the " + league.getLeague() + " are:");
        System.out.println(super.toString());
        System.out.println("\tPlease type the name of a team for more information about it.");
        inputPrompt();
    }
}
