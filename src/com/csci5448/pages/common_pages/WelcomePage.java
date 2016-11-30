package com.csci5448.pages.common_pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public class WelcomePage extends Page {

    private static final String LOGIN_ID = "login";
    private static final String SIGNUP_ID = "signup";

    public WelcomePage() {
        super.addPageAction(LOGIN_ID, o -> Controller.setCurrentPage(new LoginPage()));
        super.addPageAction(SIGNUP_ID, o -> Controller.setCurrentPage(new CreateAccountPage()));
    }

    @Override
    public void displayPage() {
        System.out.println(" ________   ______   _______  ____  _____   ______ \n" +
                           "|_   __  |.' ____ \\ |_   __ \\|_   \\|_   _|.' ___  |\n" +
                           "  | |_ \\_|| (___ \\_|  | |__) | |   \\ | | / .'   \\_|  .---.  _ .--.   \n" +
                           "  |  _| _  _.____`.   |  ___/  | |\\ \\| | | |   ____ / /__\\\\[ `.-. |\n" +
                           " _| |__/ || \\____) | _| |_    _| |_\\   |_\\ `.___]  || \\__., | | | |\n" +
                           "|________| \\______.'|_____|  |_____|\\____|`._____.'  '.__.'[___||__] ");


        System.out.println("Welcome to ESPNGen!\nPlease type \'" + LOGIN_ID + "\' to proceed to the login page, or "+
                "\'" + SIGNUP_ID + "\' to proceed to the signup page.");
    }
}
