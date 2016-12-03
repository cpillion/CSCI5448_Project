package com.csci5448.pages;

public class PageDisplay {

    private static PageDisplay pageDisplay;

    public static PageDisplay getPageDisplay() {
        if (pageDisplay == null) {
            pageDisplay = new PageDisplay();
        }
        return pageDisplay;
    }

    public void showPageWelcomeText(String pageName) {
        String buffer = getBuffer(pageName.length());
        System.out.println("\n|" + buffer + " Welcome to the " + pageName + " Page! " + buffer + "|");
    }

    public void showNavCommands() {
        System.out.println("\tPage Navigation Commands: \n" +
                "\t\tReturn to Lobby: \'" + Page.HOME_ID + "\'\n" +
                "\t\tReturn to Previous Page: \'" + Page.PREVIOUS_PAGE_ID + "\'\n" +
                "\t\tLogout: \'" + Page.LOGOUT_ID + "\'\n");
    }

    public void showInputPrompt() {
        System.out.print("\tSelection: ");
    }

    private String getBuffer(int lengthOffset) {
        return new String(new char[(100 - lengthOffset)/2]).replace("\0", "-");
    }
}
