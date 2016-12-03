package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import com.csci5448.pages.PageDisplay;
import org.hibernate.Session;

public class PlayerPage extends Page {

    private static final String ADD_FAVORITE_PLAYER_ID = "add_favorite_player";

    private final Player player;

    public PlayerPage(Player player) {
        this.player = player;
        super.addPageAction(ADD_FAVORITE_PLAYER_ID, this::addFavoritePlayerAction);
    }

    private void addFavoritePlayerAction(String arg) {
        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);

        try (Session session = Controller.sessionFactory.openSession()) {
            userAccount.addFavoritePlayer(player);
            if (!SessionManager.getSessionManager().performOp(session, session::update, userAccount)) {
                userAccount.removeFavoritePlayer(player);
                return;
            }
        }
        System.out.println("\t" + player.getName() + " has been added to your favorite players list.");
    }

    @Override
    public void displayPage() {
        System.out.println(player + "\n");
        System.out.println("\tTo add this player to your list of favorite players, please type \'" +
                ADD_FAVORITE_PLAYER_ID + "\'.");
        PageDisplay.getPageDisplay().showInputPrompt();
    }

}
