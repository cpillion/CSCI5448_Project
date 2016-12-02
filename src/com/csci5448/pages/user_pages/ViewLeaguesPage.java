package com.csci5448.pages.user_pages;

import com.csci5448.content.League;
import com.csci5448.content.Sport;

public class ViewLeaguesPage extends ViewCollectionsPage<League> {

    public ViewLeaguesPage(Sport sport) {
        super(session -> session.createQuery("FROM League WHERE sport=" + sport.ordinal()),
                League::getLeague, LeaguePage::new);
    }

    public void displayPage() {
        System.out.println("Leagues: ");
        System.out.println(super.toString());
        System.out.println("Please type the name of a league for more information about it.");
    }

}
