package com.csci5448.content.stats;

public abstract class BasicStats {

    private final int wins;
    private final int losses;
    private final int ties;

    public BasicStats(final int wins, final int losses, final int ties) {
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

}
