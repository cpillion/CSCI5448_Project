package com.csci5448.content;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @Column(name = "league")
    private String league;
    @Column
    private Sport sport;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "league", cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    public League() {}

    public League(String league, Sport sport) {
        this.league = league;
        this.sport = sport;
    }

    public void addTeam(Team team) {
        team.setLeague(this);
        teams.add(team);
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof League)) {
            return false;
        }
        League league = (League) o;
        return this.getLeague().equals(league.getLeague());
    }

    @Override
    public int hashCode() {
        if (this.getLeague() == null) {
            return -1;
        }
        return this.getLeague().hashCode();
    }
}
