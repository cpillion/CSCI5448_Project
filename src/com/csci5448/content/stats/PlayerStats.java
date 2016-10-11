package com.csci5448.content.stats;

public class PlayerStats extends BasicStats {

    private final int gamesMissed;

    public PlayerStats(final int wins, final int losses, final int ties, final int gamesMissed) {
        super(wins, losses, ties);
        this.gamesMissed = gamesMissed;
    }

    public int getGamesMissed() {
        return gamesMissed;
    }

}
