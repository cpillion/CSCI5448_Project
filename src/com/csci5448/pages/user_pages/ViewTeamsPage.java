package com.csci5448.pages.user_pages;

import com.csci5448.content.Sport;
import com.csci5448.content.Team;
import com.csci5448.pages.PageDisplay;

public class ViewTeamsPage extends ViewCollectionsPage<Team> {

    private final Sport sport;

    public ViewTeamsPage(Sport sport) {
        super(session -> session.createQuery("FROM Team where sport=" + sport.ordinal(), Team.class),
                Team::getName, TeamPage::new);
        this.sport = sport;
    }

    public void displayPage() {
        PageDisplay.getPageDisplay().showPageWelcomeText(sport + " Teams");
        PageDisplay.getPageDisplay().showNavCommands();
        System.out.println("\tThe current teams available are: ");
        System.out.println(super.toString());
        System.out.println("\tPlease type the name of a team for more information about it.");
        PageDisplay.getPageDisplay().showInputPrompt();
    }

}
