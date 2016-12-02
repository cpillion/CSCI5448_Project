package com.csci5448.content.stats;

import javax.persistence.*;

@MappedSuperclass
abstract class BasicStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int wins;
    @Column
    private int losses;
    @Column
    private int ties;

    public BasicStats() {}

    BasicStats(final int wins, final int losses, final int ties) {
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    @Override
    public String toString() {
        return "Wins: " + wins + "\t\t" +
                "Losses: " + losses + "\n" +
                "Ties: " + ties;
    }

}
