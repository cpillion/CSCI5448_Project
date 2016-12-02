package com.csci5448.pages;

import com.csci5448.control.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Page {

    public static final String PREVIOUS_PAGE_ID = "previous_page";
    public static final String LOGOUT_ID = "logout";
    public static final String HOME_ID = "home";

    private Map<String, Consumer> pageActions;

    public Page() {
        pageActions = new HashMap<>();
    }

    public <T> void addPageAction(String identifier, Consumer<T> pageAction) {
        pageActions.put(identifier.toLowerCase(), pageAction);
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
        if (identifier.equals(HOME_ID)) {
            Controller.goToLobbyPage();
            return;
        }

        Consumer pageAction = pageActions.get(identifier);
        if (pageAction != null) {
            try {
                pageAction.accept(arg);
            } catch (ClassCastException e) { //this can happen if the wrong number/type of arguments are passed
                throw e;
            }
        }
    }

    public abstract void displayPage();

    public void makeNewPage(String pageName) {
        System.out.println("\n|------------------------ Welcome to the " + pageName + " Page! ------------------------|");}

}
