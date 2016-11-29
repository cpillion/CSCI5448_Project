package com.csci5448.main;

import com.csci5448.accounts.Credentials;
import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.LoginPage;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Controller.setCurrentPage(new LoginPage());
        Scanner userInput = new Scanner(System.in);
        while (true) {
            Controller.processUserInput(userInput.nextLine());
        }
    }

}
