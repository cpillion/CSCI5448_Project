package com.csci5448.pages.user_pages.user_page_actions;

import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.PageAction;
import com.csci5448.pages.user_pages.SportLobbyPage;

public class SelectSportAction extends PageAction<Sport> {

    public SelectSportAction(String identifier) {
        super(identifier);
    }

    public void performAction(Sport sport) {
        Controller.setCurrentSport(sport);
        Controller.setCurrentPage(new SportLobbyPage());
    }

}
