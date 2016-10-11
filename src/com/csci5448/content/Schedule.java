package com.csci5448.content;

import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private final List<Game> schedule;

    public Schedule(List<Game> schedule) {
        this.schedule = schedule;
    }

    public List<Game> getTeamSchedule(final String team) {
        return schedule.stream().filter(game -> game.containsTeam(team)).collect(Collectors.toList());
    }

    public List<Game> getSchedule() {
        return schedule;
    }

}
