package com.csci5448.content;

import com.csci5448.content.stats.PlayerStats;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Sport sport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerStats stats;
    @Column
    private String name;
    @Column
    private String status;

    public Player() {}

    public Player(Sport sport, Team team, String name, String status, PlayerStats stats) {
        this.sport = sport;
        this.team = team;
        this.name = name;
        this.status = status;
        this.stats = stats;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
