package com.csci5448.content.stats;

public class TeamStats extends BasicStats {

    private final int numInjuredPlayers;

    public TeamStats(final int wins, final int losses, final int ties, final int numInjuredPlayers) {
        super(wins, losses, ties);
        this.numInjuredPlayers = numInjuredPlayers;
    }

    public int getNumInjuredPlayers() {
        return numInjuredPlayers;
    }

}
