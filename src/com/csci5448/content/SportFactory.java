package com.csci5448.content;

public class SportFactory {

    public static Sport chooseSport(String type) {

        Sport sport = null;

        if (type.equalsIgnoreCase("BASKETBALL"))
            sport = Sport.BASKETBALL;
        else if (type.equalsIgnoreCase("BASEBALL"))
            sport = Sport.BASEBALL;
        else if (type.equalsIgnoreCase("FOOTBALL"))
            sport = Sport.FOOTBALL;

        return sport;
    }

}
