package com.csci5448.content;

import com.csci5448.content.stats.TeamStats;

public class Team {

    private final Sport sport;
    private final String name;
    private final TeamStats stats;

    public Team(Sport sport, String name, TeamStats stats) {
        this.sport = sport;
        this.name = name;
        this.stats = stats;
    }

    public Team(Sport sport, String name) {
        this.sport = sport;
        this.name = name;
        this.stats = null;
    }

    public Sport getSport() {
        return sport;
    }

    public String getName() {
        return name;
    }

    public TeamStats getStats() {
        return stats;
    }

}
