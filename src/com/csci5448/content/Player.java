package com.csci5448.content;

import com.csci5448.content.stats.PlayerStats;
import com.csci5448.pages.user_pages.SportOptionPage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player implements SportItem {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Sport sport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "player", cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        if ((this.getId() == null && player.getId() != null) || (player.getId() == null && this.getId() != null)) {
            return false;
        }
        if (this.getId() == null && player.getId() == null) {
            return this.getSport() == player.getSport() && this.getTeam().equals(player.getTeam()) &&
                    this.getName().equals(player.getName());
        }
        return this.getId().equals(player.getId());
    }

    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return Objects.hash(this.getSport(), this.getTeam(), this.getName());
        }
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\t\t" +
                "Team: " + team.getName() + "\n" +
                "Sport: " + sport + "\t\t" +
                "Status: " + status + "\n" +
                stats.toString();
    }
}
