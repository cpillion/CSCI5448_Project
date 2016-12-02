package com.csci5448.pages.user_pages;

import com.csci5448.content.League;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ViewLeaguesPage extends Page {

    private final List<League> leagues;

    public ViewLeaguesPage(Sport sport) {
        try (Session session = Controller.sessionFactory.openSession()) {
            Query<League> leagueQuery = session.createQuery("FROM League WHERE sport=" + sport.ordinal(), League.class);
            leagues = leagueQuery.list();
        }
        for (League league : leagues) {
            super.addPageAction(league.getLeague(), this::selectLeagueAction);
        }
    }

    private void selectLeagueAction(String leagueName) {
        List<League> filteredLeagues = leagues.stream().filter(league ->
                league.getLeague().equalsIgnoreCase(leagueName)).collect(Collectors.toList());

        if (filteredLeagues.size() == 0) {
            return;
        }
        Controller.setCurrentPage(new LeaguePage(filteredLeagues.get(0)));
    }

    public void displayPage() {
        System.out.println("Leagues: ");
        for (League league : leagues) {
            System.out.println("\t" + league.getLeague());
        }
        System.out.println("Please type the name of a league for more information about it.");
    }

}
