package com.csci5448.pages.user_pages;

import com.csci5448.content.Team;
import com.csci5448.pages.Page;

public class ViewTeamsPage extends Page {

    public static final String SELECT_TEAM_ID = "select_team";

    public ViewTeamsPage() {
        super.addPageAction(SELECT_TEAM_ID, this::selectTeamAction);
    }

    private void selectTeamAction(Team team) {

    }

    public void displayPage() {
        //TODO
    }

}
