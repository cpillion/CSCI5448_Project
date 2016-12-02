package com.csci5448.pages.common_pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public class LogoutPage extends Page {

    private static final String CONFIRM_LOGOUT_ID = "confirm";

    public LogoutPage() {
        super.addPageAction(CONFIRM_LOGOUT_ID, o -> Controller.logout());
    }

    @Override
    public boolean freezeInput(String identifier, Object arg) {
        if (!identifier.equalsIgnoreCase(CONFIRM_LOGOUT_ID)) {
            System.out.println("Account will not be logged out.");
            Controller.returnToPreviousPage();
            return true;
        }
        return false;
    }

    public void displayPage() {
        System.out.println("Type \'" + CONFIRM_LOGOUT_ID + "\' to confirm your logout.");
    }

}
