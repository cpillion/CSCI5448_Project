package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.LobbyPage;

public class UserLobbyPage extends LobbyPage {

    public UserLobbyPage() {
        super(UserAccount.class);
        for (Sport sport : Sport.values()) {
            super.addPageAction(sport.toString(), selectedSportStr -> {
                Sport selectedSport = SportFactory.chooseSport(selectedSportStr);
                if (selectedSport != null) {
                    Controller.setCurrentPage(new SportOptionPage(selectedSport));
                }
            });
        }
    }

    public void displayPage() {
        makeNewPage("ESPNGen Lobby");
        System.out.println("\tPlease select a sport from the menu below:");
        for (Sport sport : Sport.values()) {
            String sportStr = sport.toString();
            System.out.println("\t\t" + sportStr.substring(0, 1) + sportStr.substring(1).toLowerCase());
        }
        super.displayPage();
        inputPrompt();
    }

}
