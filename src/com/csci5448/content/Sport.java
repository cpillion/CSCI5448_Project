package com.csci5448.content;

public enum Sport {
    FOOTBALL,
    BASKETBALL,
    BASEBALL;

    @Override
    public String toString() {
        String sport = super.toString();
        return sport.substring(0,1).toUpperCase() + sport.substring(1).toLowerCase();
    }
}
