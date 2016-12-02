package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.pages.Page;

import javax.mail.MessagingException;

public class SupportRequestPage extends Page {

    private static final String SUBMIT_REQUEST_ID = "submit_request";

    public SupportRequestPage() {
        super.addPageAction(SUBMIT_REQUEST_ID, this::requestSupportAction);
    }

    private void requestSupportAction(String[] input) {
        if (input == null || input.length == 0) {
            return;
        }

        Account currentAccount = Controller.getCurrentAccount();
        if (currentAccount == null) {
            return;
        }

        final String supportRequest = String.join(" ", input);
        final StringBuilder messageBodyBuilder = new StringBuilder();
        messageBodyBuilder.append("User: " + currentAccount.getUsername() + "\n\n");
        messageBodyBuilder.append(supportRequest + "\n\n");
        messageBodyBuilder.append("Instructions: Forward this email to " + currentAccount.getUsername() + " together " +
                "with your reply.");

        System.out.println("Sending support request...");

        try {
            EmailControl.getEmailControl().sendEmail("espngen@gmail.com",
                    "Support Request From " + currentAccount.getUsername(), messageBodyBuilder.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("You support request has been sent to an administrator and will be reviewed shortly.\n" +
                "Please periodically check your inbox at " + currentAccount.getUsername() + " for a reply.");
    }

    @Override
    public void displayPage() {
        System.out.println("Welcome to the support request page!\nPlease type \'" + SUBMIT_REQUEST_ID + "\' followed" +
                " by a description of the problem you are experiencing.");
    }
}
