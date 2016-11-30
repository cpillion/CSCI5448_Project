package com.csci5448.main;

import com.csci5448.content.Sport;
import com.csci5448.content.Team;
import com.csci5448.content.stats.PlayerStats;
import com.csci5448.content.stats.TeamStats;
import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.WelcomePage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller.initSessionFactory();

        //RUN ME ONLY ONCE TO INITIALLY POPULATE DB.
        //RUNNING ME MORE THAN ONCE WILL LEAD TO PROBLEMS.
        //populateSports();

        Controller.setCurrentPage(new WelcomePage());
        Scanner userInput = new Scanner(System.in);
        while (true) {
            processUserInput(userInput.nextLine());
        }
    }

    private static void processUserInput(String input) {
        if (!input.contains(" ")) {
            Controller.sendCommandToPage(input, input);
            return;
        }

        int endOfCommandIndex = input.indexOf(" ");
        String command = input.substring(0, endOfCommandIndex);
        String[] args = input.substring(endOfCommandIndex+1).split(" ");

        if (args.length == 1) {
            try {
                Controller.sendCommandToPage(command, args[0]);
            } catch (ClassCastException e) {
                Controller.sendCommandToPage(command, args);
            }
        } else {
            Controller.sendCommandToPage(command, args);
        }
    }

    private static void populateSports() {
        Team footballTeamA = new Team(Sport.FOOTBALL, "Broncos", new TeamStats(10, 0, 0, 0));
        footballTeamA.addPlayer("FAPlayer1", "Healthy", new PlayerStats(10, 5, 0, 0));
        footballTeamA.addPlayer("APlayer2", "Healthy", new PlayerStats(10, 2, 2, 2));

        Team footballTeamB = new Team(Sport.FOOTBALL, "Chargers", new TeamStats(5, 3, 2, 1));
        footballTeamB.addPlayer("FBPlayer1", "Injured", new PlayerStats(200, 5, 3, 4));
        footballTeamB.addPlayer("FBPlayer2", "Healthy", new PlayerStats(10, 2, 4, 3));

        Team basketballTeamA = new Team(Sport.BASKETBALL, "Lakers", new TeamStats(0, 5, 5, 8));
        basketballTeamA.addPlayer("BAPlayer1", "Injured", new PlayerStats(0, 1, 2, 3));
        basketballTeamA.addPlayer("BAPlayer2", "Injured", new PlayerStats(5, 50, 500, 5000));

        Team basketballTeamB = new Team(Sport.BASKETBALL, "Nuggets", new TeamStats(8, 0, 2, 3));
        basketballTeamB.addPlayer("BBPlayer1", "Healthy", new PlayerStats(10, 11, 12, 13));
        basketballTeamB.addPlayer("BBPlayer2", "Injured", new PlayerStats(100, 1000, 105, 112));

        Team baseballTeamA = new Team(Sport.BASEBALL, "Padres", new TeamStats(0, 8, 2, 0));
        baseballTeamA.addPlayer("BAAPlayer1", "Healthy", new PlayerStats(12, 13, 14, 15));
        baseballTeamA.addPlayer("BAAPlayer2", "Healthy", new PlayerStats(120, 130, 140, 150));

        Team baseballTeamB = new Team(Sport.BASEBALL, "Rockies", new TeamStats(6, 2, 2, 2));
        baseballTeamB.addPlayer("BABPlayer1", "Healthy", new PlayerStats(41, 23, 54, 17));
        baseballTeamB.addPlayer("BABPlayer2", "Healthy", new PlayerStats(33, 333, 3333, 33333));

        try (Session session = Controller.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(footballTeamA);
            session.save(footballTeamB);
            session.save(baseballTeamA);
            session.save(baseballTeamB);
            session.save(basketballTeamA);
            session.save(basketballTeamB);
            transaction.commit();
        }
    }

}
