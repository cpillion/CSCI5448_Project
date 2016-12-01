package com.csci5448.content.stats;

import com.csci5448.content.Team;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teamstats")
public class TeamStats extends BasicStats implements Serializable {

    @Column
    private int numInjuredPlayers;

    @GenericGenerator(name = "teamgenerator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "team"))
    @Id
    @GeneratedValue(generator = "teamgenerator")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Team team;

    public TeamStats() {}

    public TeamStats(final int wins, final int losses, final int ties, final int numInjuredPlayers) {
        super(wins, losses, ties);
        this.numInjuredPlayers = numInjuredPlayers;
    }

    public int getNumInjuredPlayers() {
        return numInjuredPlayers;
    }

    public void setNumInjuredPlayers(int numInjuredPlayers) {
        this.numInjuredPlayers = numInjuredPlayers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        String basicStats = super.toString();
        return basicStats + "\nInjured Players Count: " + numInjuredPlayers;
    }

}
