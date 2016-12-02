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

    private Map<String, PageAction<?>> pageActions;

    public Page() {
        pageActions = new HashMap<>();
        addPageAction(PREVIOUS_PAGE_ID, o -> Controller.returnToPreviousPage());
        addPageAction(HOME_ID, o -> Controller.goToLobbyPage());
        addPageAction(LOGOUT_ID, this::performLogoutAction);
    }

    public void addPageAction(String identifier, Consumer<String[]> pageAction) {
        pageActions.put(identifier.toLowerCase(), new PageAction<>(pageAction, arr -> arr));
    }

    public void addPageActionString(String identifier, Consumer<String> pageAction) {
        pageActions.put(identifier.toLowerCase(), new PageAction<>(pageAction, arr -> String.join(" ", arr).trim()));
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

    public void performAction(String identifier, String[] args) {
        if (freezeInput(identifier, args)) {
            return;
        }
        PageAction<?> pageAction = pageActions.get(identifier);
        if (pageAction != null) {
            pageAction.accept(args);
        } else {
            performDefaultAction(identifier, args);
        }
    }

    public boolean freezeInput(String identifier, String[] args) {
        return false;
    }

    public void performDefaultAction(String identifier, String[] args) {}

    public abstract void displayPage();

}
