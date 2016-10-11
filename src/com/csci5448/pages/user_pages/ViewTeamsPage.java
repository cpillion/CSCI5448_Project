package com.csci5448.pages.user_pages;

import com.csci5448.pages.Page;
import com.csci5448.pages.user_pages.user_page_actions.SelectTeamAction;

public class ViewTeamsPage extends Page {

    public static final String SELECT_TEAM_ID = "select_team";

    public ViewTeamsPage() {
        super.addPageAction(new SelectTeamAction(SELECT_TEAM_ID));
    }

    public void displayPage() {
        //TODO
    }

}
