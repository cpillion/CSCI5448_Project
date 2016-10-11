package com.csci5448.content;

import com.csci5448.content.stats.PlayerStats;

public class Player {

    private final Sport sport;
    private final String team;
    private final PlayerStats stats;
    private final String name;
    private final String status;

    public Player(Sport sport, String team, PlayerStats stats, String name, String status) {
        this.sport = sport;
        this.team = team;
        this.stats = stats;
        this.name = name;
        this.status = status;
    }

    public Player(Sport sport, String team, String name) {
        this.sport = sport;
        this.team = team;
        this.name = name;
        this.stats = null;
        this.status = null;
    }

    public Sport getSport() {
        return sport;
    }

    public String getTeam() {
        return team;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
