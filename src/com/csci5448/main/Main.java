package com.csci5448.main;

import com.csci5448.control.Controller;
import com.csci5448.pages.common_pages.WelcomePage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller.initSessionFactory();

        Controller.setCurrentPage(new WelcomePage());
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
