package com.csci5448.pages;

import com.csci5448.control.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {

    public static final String PREVIOUS_PAGE_ID = "previous_page";
    public static final String LOGOUT_ID = "logout";

    private List<PageAction> pageActions;

    public Page() {
        pageActions = new ArrayList<>();
    }

    public void addPageAction(PageAction action) {
        pageActions.add(action);
    }

    public void performAction(String identifier, Object arg) {
        if (identifier.equals(PREVIOUS_PAGE_ID)) {
            Controller.returnToPreviousPage();
            return;
        }
        if (identifier.equals(LOGOUT_ID)) {
            Controller.logout();
            return;
        }

        for (PageAction pageAction: pageActions) {
            if (!pageAction.getIdentifier().equals(identifier)) {
                continue;
            }
            pageAction.performAction(arg);
        }
    }

    public abstract void displayPage();

}
