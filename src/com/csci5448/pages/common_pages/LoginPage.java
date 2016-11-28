package com.csci5448.pages.common_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.common_pages.JournalistLoginAction;
import com.csci5448.pages.common_pages.UserLoginAction;

public class LoginPage extends Page {

    public static final String USER_LOGIN_ID = "user_login";
    public static final String JOURNALIST_LOGIN_ID = "journalist_login";

    public LoginPage() {
        super.addPageAction(new UserLoginAction(USER_LOGIN_ID));
        super.addPageAction(new JournalistLoginAction(JOURNALIST_LOGIN_ID));
    }

    public void displayPage() {
        System.out.println("You are on the login page!");
        //TODO Either graphically display this, or do it better in cli.
    }

}
