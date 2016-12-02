package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.DeleteAccountPage;

public class UserLobbyPage extends Page {

    private static final String DELETE_ACCOUNT_ID = "delete_account";

    public UserLobbyPage() {
        for (Sport sport : Sport.values()) {
            super.addPageAction(sport.toString(), selectedSportStr -> {
                Sport selectedSport = SportFactory.chooseSport(selectedSportStr.toString());
                if (selectedSport != null) {
                    Controller.setCurrentPage(new SportOptionPage(selectedSport));
                }
            });
        }
        super.addPageAction(DELETE_ACCOUNT_ID, o -> Controller.setCurrentPage(
                new DeleteAccountPage<>(Controller.getCurrentAccount(UserAccount.class))));
    }

    public void displayPage() {
        System.out.println("Welcome to the ESPNGen Lobby Page!\n" + "Please select a sport from the menu below:");
        for (Sport sport : Sport.values()) {
            System.out.println("\t" + sport.toString().toLowerCase());
        }
        System.out.println("If you wish to delete your account, please type \'" + DELETE_ACCOUNT_ID + "\'.");
        System.out.print("Selection: ");
    }

}
