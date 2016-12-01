package com.csci5448.content;

import com.csci5448.content.stats.PlayerStats;
import com.csci5448.content.stats.TeamStats;
import com.csci5448.pages.user_pages.SportOptionPage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team implements SportItem {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Sport sport;
    @Column
    private String name;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.ALL)
    private TeamStats teamStats;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.MERGE)
    private Set<Player> players = new HashSet<>(0);

    public Team() {}

    public Team(Sport sport, String name, TeamStats teamStats) {
        this.sport = sport;
        this.name = name;
        this.teamStats = teamStats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(String name, String status, PlayerStats stats) {
        players.add(new Player(sport, this, name, status, stats));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Team)) {
            return false;
        }
        Team team = (Team) o;
        return this.getId().equals(team.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + "\n");
        sb.append("Sport: " + sport + "\n");
        sb.append(teamStats.toString());
        return sb.toString();
    }

}
