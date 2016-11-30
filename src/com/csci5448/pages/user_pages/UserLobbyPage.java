package com.csci5448.pages.user_pages;

import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public class UserLobbyPage extends Page {

    public UserLobbyPage() {
        for (Sport sport : Sport.values()) {
            super.addPageAction(sport.toString().toLowerCase(), selectedSportStr -> {
                Sport selectedSport = SportFactory.chooseSport(selectedSportStr.toString());
                if (selectedSport != null) {
                    Controller.setCurrentPage(new SportOptionPage(selectedSport));
                }
            });
        }
    }

    public void displayPage() {
        System.out.println("Welcome to the ESP-NGen Lobby Page!\n" + "Please select a sport from the menu below:");
        for (Sport sport : Sport.values()) {
            System.out.println("\t" + sport.toString().toLowerCase());
        }
        System.out.print("Selection: ");
    }

}
