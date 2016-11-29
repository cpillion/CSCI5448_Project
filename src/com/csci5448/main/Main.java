package com.csci5448.main;

import com.csci5448.accounts.Credentials;
import com.csci5448.accounts.UserAccount;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.LoginPage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Controller.initSessionFactory();

        System.out.println("Welcome to ESPNGen!\nWhile navigating through the pages,"
                + " please type \'" + Page.PREVIOUS_PAGE_ID + "\' at any time to return to the previous page.");
        Controller.setCurrentPage(new LoginPage());
        Scanner userInput = new Scanner(System.in);
        while (true) {
            processUserInput(userInput.nextLine());
        }
    }

    private static void processUserInput(String input) {
        if (!input.contains(" ")) {
            Controller.sendCommandToPage(input, null);
            return;
        }

        int endOfCommandIndex = input.indexOf(" ");
        String command = input.substring(0, endOfCommandIndex);
        String[] args = input.substring(endOfCommandIndex+1).split(" ");

        if (args.length == 1) {
            Controller.sendCommandToPage(command, args[0]);
        } else {
            Controller.sendCommandToPage(command, args);
        }
    }

}
