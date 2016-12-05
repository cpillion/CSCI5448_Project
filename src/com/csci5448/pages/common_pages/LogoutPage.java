package com.csci5448.pages.common_pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.PageDisplay;

public class LogoutPage extends Page {

    private static final String CONFIRM_LOGOUT_ID = "confirm";

    public LogoutPage() {
        super.addPageAction(CONFIRM_LOGOUT_ID, arg -> Controller.logout());
    }

    @Override
    public boolean freezeInput(String identifier, String args) {
        if (!identifier.equalsIgnoreCase(CONFIRM_LOGOUT_ID)) {
            System.out.println("\tAccount will not be logged out.");
            Controller.returnToPreviousPage();
            return true;
        }
        return false;
    }

    public void displayPage() {

        System.out.println("\tType \'" + CONFIRM_LOGOUT_ID + "\' to confirm your logout.");
        PageDisplay.getPageDisplay().showInputPrompt();
    }

}
