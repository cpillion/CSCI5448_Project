package com.csci5448.main;

import com.csci5448.accounts.Credentials;
import com.csci5448.control.Controller;
import com.csci5448.pages.LoginPage;

public class Main {

    public static void main(String[] args) {
        Controller.setCurrentPage(new LoginPage());
        Controller.sendCommandToPage("user_login", new Credentials("abc", "def"));
    }

}
