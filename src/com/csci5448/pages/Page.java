package com.csci5448.pages;

import com.csci5448.control.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Page {

    public static final String PREVIOUS_PAGE_ID = "previous_page";
    public static final String LOGOUT_ID = "logout";

    private Map<String, Consumer> pageActions;

    public Page() {
        pageActions = new HashMap<>();
    }

    public <T> void addPageAction(String identifier, Consumer<T> pageAction) {
        pageActions.put(identifier, pageAction);
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

        Consumer pageAction = pageActions.get(identifier);
        if (pageAction != null) {
            pageAction.accept(arg);
        }
    }

    public abstract void displayPage();

}
