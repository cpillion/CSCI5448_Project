package com.csci5448.pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.LogoutPage;
import com.sun.tools.javac.util.StringUtils;

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
        addPageAction(PREVIOUS_PAGE_ID, arg -> Controller.returnToPreviousPage());
        addPageAction(HOME_ID, arg -> Controller.goToLobbyPage());
        addPageAction(LOGOUT_ID, this::performLogoutAction);
    }

    public void addPageActionStringArr(String identifier, Consumer<String[]> pageAction) {
        pageActions.put(identifier.toLowerCase(), new PageAction<>(pageAction, str -> str.split(" ")));
    }

    public void addPageAction(String identifier, Consumer<String> pageAction) {
        pageActions.put(identifier.toLowerCase(), new PageAction<>(pageAction, str -> str));
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

    private void performLogoutAction(String arg) {
        if (Controller.getCurrentAccount() == null) {
            return;
        }
        Controller.setCurrentPage(new LogoutPage());
    }

    public void performAction(String identifier, String arg) {
        if (freezeInput(identifier, arg)) {
            return;
        }
        PageAction<?> pageAction = pageActions.get(identifier);
        if (pageAction != null) {
            pageAction.accept(arg);
        } else {
            performDefaultAction(identifier, arg);
        }
    }

    public boolean freezeInput(String identifier, String arg) {
        return false;
    }

    public void performDefaultAction(String identifier, String arg) {}

    public abstract void displayPage();

    public void makeNewPage(String pageName) {
        int buffLen = (90 - pageName.length())/2;
        String buffer = new String(new char[buffLen]).replace("\0", "-");
        System.out.println("\n|" + buffer + " Welcome to the " + pageName + " Page! " + buffer + "|");
    }

    public void inputPrompt() {
        System.out.print("\tSelection: ");
    }

}
