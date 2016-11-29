package com.csci5448.pages.common_pages;

import com.csci5448.pages.Page;

public class CreateAccountPage extends Page {

    private enum AccountType {
        USER, JOURNALIST
    }

    private static final String CREATE_ACCOUNT_ID = "create_account";
    private static final String SET_ACCOUNT_TYPE_ID = "account_type";

    private static final String JOURNALIST_ACCOUNT_TYPE = "journalist";
    private static final String USER_ACCOUNT_TYPE = "user";

    private AccountType accountType;

    public CreateAccountPage() {
        super.addPageAction(SET_ACCOUNT_TYPE_ID, this::setAccountType);
        super.addPageAction(CREATE_ACCOUNT_ID, this::createAccount);
    }

    private void setAccountType(String type) {
        String lowerType = type.toLowerCase();
        switch (lowerType) {
            case JOURNALIST_ACCOUNT_TYPE:
                accountType = AccountType.JOURNALIST;
                break;
            case USER_ACCOUNT_TYPE:
                accountType = AccountType.USER;
                break;
            default:
                return;
        }
        System.out.println("Account type set to " + lowerType);
        System.out.println("Please type \'" + CREATE_ACCOUNT_ID + " <username> <password>\' to create your account.");
    }

    private void createAccount(String[] credentials) {
        if (accountType == null) {
            return;
        }
        System.out.println("Creating account " + credentials[0] + "...");
    }

    @Override
    public void displayPage() {
        System.out.println("Welcome to the account creation page!");
        System.out.println("Please type \'" + SET_ACCOUNT_TYPE_ID + " <type> \' to begin your account creation.");
        System.out.println("<type> must be either \'" + JOURNALIST_ACCOUNT_TYPE + "\' or \'" + USER_ACCOUNT_TYPE + "\'");
    }
}
