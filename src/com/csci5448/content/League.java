package com.csci5448.content;

import java.util.List;
import java.util.stream.Collectors;

public class League {

    private final List<Team> standings;
    private final Schedule schedule;
    private final List<Player> players;

    public League(List<Team> standings, Schedule schedule, List<Player> players) {
        this.standings = standings;
        this.schedule = schedule;
        this.players = players;
    }

    public List<Team> getStandings() {
        return standings;
    }

    public List<Player> getPlayersOnTeam(String team) {
        return players.stream().filter(player -> player.getTeam().equals(team)).collect(Collectors.toList());
    }

    public Schedule getSchedule() {
        return schedule;
    }

}
