package com.csci5448.content;

import java.util.Date;

public class Game {

    private final Date date;
    private final String homeTeam;
    private final String awayTeam;
    private final String gameType; //e.g. regular season, playoffs, etc.

    public Game(Date date, String homeTeam, String awayTeam, String gameType) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameType = gameType;
    }

    public boolean containsTeam(final String team) {
        return homeTeam.equals(team) || awayTeam.equals(team);
    }

    public Date getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getGameType() {
        return gameType;
    }

}
