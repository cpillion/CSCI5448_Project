package com.csci5448.content.stats;

import com.csci5448.content.Player;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "playerstats")
public class PlayerStats extends BasicStats {

    private int gamesMissed;

    @GenericGenerator(name = "playerenerator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "player"))
    @Id
    @GeneratedValue(generator = "playergenerator")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Player player;

    public PlayerStats() {}

    public PlayerStats(final int wins, final int losses, final int ties, final int gamesMissed) {
        super(wins, losses, ties);
        this.gamesMissed = gamesMissed;
    }

    public int getGamesMissed() {
        return gamesMissed;
    }

    public void setGamesMissed(int gamesMissed) {
        this.gamesMissed = gamesMissed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        String basicStats = super.toString();
        return basicStats + "\t\tGames Missed: " + gamesMissed;
    }

}
