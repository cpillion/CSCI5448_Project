package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;

public abstract class LobbyPage extends Page {

    private static final String DELETE_ACCOUNT_ID = "delete_account";
    private static final String SUPPORT_REQUEST_ID = "request_support";

    public <T extends Account> LobbyPage(Class<T> accountClass) {
        super.addPageAction(DELETE_ACCOUNT_ID, arg -> Controller.setCurrentPage(
                new DeleteAccountPage<>(Controller.getCurrentAccount(accountClass))));
        super.addPageAction(SUPPORT_REQUEST_ID, arg -> Controller.setCurrentPage(new SupportRequestPage()));
    }

    public void displayPage() {
        System.out.println("If you wish to delete your account, please type \'" + DELETE_ACCOUNT_ID + "\'.");
        System.out.println("If you require assistance from an administrator, please visit the support request page " +
                "by typing \'" + SUPPORT_REQUEST_ID + "\'.");
    }

}
