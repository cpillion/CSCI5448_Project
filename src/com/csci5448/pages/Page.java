package com.csci5448.pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.LogoutPage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Page {

    protected static final String PREVIOUS_PAGE_ID = "previous_page";
    protected static final String LOGOUT_ID = "logout";
    protected static final String HOME_ID = "home";

    private Map<String, Consumer> pageActions;

    public Page() {
        pageActions = new HashMap<>();
        pageActions.put(PREVIOUS_PAGE_ID.toLowerCase(), o -> Controller.returnToPreviousPage());
        pageActions.put(HOME_ID.toLowerCase(), o -> Controller.goToLobbyPage());
        pageActions.put(LOGOUT_ID.toLowerCase(), this::performLogoutAction);
    }

    public <T> void addPageAction(String identifier, Consumer<T> pageAction) {
        pageActions.put(identifier.toLowerCase(), pageAction);
    }

    protected boolean removePageAction(String identifier) {
        if (pageActions.get(identifier) != null) {
            pageActions.remove(identifier);
            return true;
        }
        return false;
    }

    protected boolean containsPageAction(String identifier) {
        return pageActions.get(identifier) != null;
    }

    private void performLogoutAction(Object o) {
        if (Controller.getCurrentAccount() == null) {
            return;
        }
        Controller.setCurrentPage(new LogoutPage());
    }

    public void performAction(String identifier, Object arg) throws ClassCastException {
        if (freezeInput(identifier, arg)) {
            return;
        }
        Consumer pageAction = pageActions.get(identifier);
        if (pageAction != null) {
            pageAction.accept(arg);
        } else {
            performDefaultAction(identifier, arg);
        }
    }

    public boolean freezeInput(String identifier, Object arg) {
        return false;
    }

    public void performDefaultAction(String identifier, Object arg) {}

    public abstract void displayPage();

}
