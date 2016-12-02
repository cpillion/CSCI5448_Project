package com.csci5448.main;

import com.csci5448.content.League;
import com.csci5448.content.Sport;
import com.csci5448.content.Team;
import com.csci5448.content.stats.PlayerStats;
import com.csci5448.content.stats.TeamStats;
import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.WelcomePage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

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
            try {
                Controller.sendCommandToPage(input, input);
            } catch (ClassCastException e) {
                Controller.sendCommandToPage(input, new String[] {input});
            }
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
        League nfl = new League("nfl", Sport.FOOTBALL);
        Team footballTeamA = new Team(Sport.FOOTBALL, "Broncos", new TeamStats(10, 0, 0, 0));
        nfl.addTeam(footballTeamA);
        footballTeamA.addPlayer("FAPlayer1", "Healthy", new PlayerStats(10, 5, 0, 0));
        footballTeamA.addPlayer("FAPlayer2", "Healthy", new PlayerStats(10, 2, 2, 2));

        Team footballTeamB = new Team(Sport.FOOTBALL, "Chargers", new TeamStats(5, 3, 2, 1));
        nfl.addTeam(footballTeamB);
        footballTeamB.addPlayer("FBPlayer1", "Injured", new PlayerStats(200, 5, 3, 4));
        footballTeamB.addPlayer("FBPlayer2", "Healthy", new PlayerStats(10, 2, 4, 3));

        League football2 = new League("Football2", Sport.FOOTBALL);
        Team football2Team = new Team(Sport.FOOTBALL, "Football2Team", new TeamStats(98, 55, 21, 67));
        football2.addTeam(football2Team);
        football2Team.addPlayer("Football2TeamPlayer", "Bored", new PlayerStats(61, 20, 65, 39));

        League nba = new League("nba", Sport.BASKETBALL);

        Team basketballTeamA = new Team(Sport.BASKETBALL, "Lakers", new TeamStats(0, 5, 5, 8));
        nba.addTeam(basketballTeamA);
        basketballTeamA.addPlayer("BAPlayer1", "Injured", new PlayerStats(0, 1, 2, 3));
        basketballTeamA.addPlayer("BAPlayer2", "Injured", new PlayerStats(5, 50, 500, 5000));

        Team basketballTeamB = new Team(Sport.BASKETBALL, "Nuggets", new TeamStats(8, 0, 2, 3));
        nba.addTeam(basketballTeamB);
        basketballTeamB.addPlayer("BBPlayer1", "Healthy", new PlayerStats(10, 11, 12, 13));
        basketballTeamB.addPlayer("BBPlayer2", "Injured", new PlayerStats(100, 1000, 105, 112));

        League mlb = new League("mlb", Sport.BASEBALL);

        Team baseballTeamA = new Team(Sport.BASEBALL, "Padres", new TeamStats(0, 8, 2, 0));
        mlb.addTeam(baseballTeamA);
        baseballTeamA.addPlayer("BAAPlayer1", "Healthy", new PlayerStats(12, 13, 14, 15));
        baseballTeamA.addPlayer("BAAPlayer2", "Healthy", new PlayerStats(120, 130, 140, 150));

        Team baseballTeamB = new Team(Sport.BASEBALL, "Rockies", new TeamStats(6, 2, 2, 2));
        mlb.addTeam(baseballTeamB);
        baseballTeamB.addPlayer("BABPlayer1", "Healthy", new PlayerStats(41, 23, 54, 17));
        baseballTeamB.addPlayer("BABPlayer2", "Healthy", new PlayerStats(33, 333, 3333, 33333));

        try (Session session = Controller.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(nfl);
            session.save(football2);
            session.save(nba);
            session.save(mlb);
            transaction.commit();
        }
    }

}
