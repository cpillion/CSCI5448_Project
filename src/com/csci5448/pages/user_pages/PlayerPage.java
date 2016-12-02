package com.csci5448.pages.user_pages;

import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.content.Team;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import java.util.stream.Collectors;

public class PlayerPage extends Page {

    public static final String ADD_FAVORITE_PLAYER_ID = "add_favorite_player";

    private final Player player;

    public PlayerPage(Player player) {
        this.player = player;
        super.addPageAction(ADD_FAVORITE_PLAYER_ID, this::addFavoritePlayerAction);
    }

    private void addFavoritePlayerAction(Object o) {
        UserAccount userAccount = Controller.getCurrentAccount(UserAccount.class);

        Player updatePlayer = player;

        for (Team team : userAccount.getFavoriteTeams()) {
            if (player.getTeam().equals(team)) {
                updatePlayer = team.getPlayers().stream().filter(player ->
                        player.equals(this.player)).collect(Collectors.toList()).get(0);
                break;
            }
        }

        try (Session session = Controller.sessionFactory.openSession()) {
            userAccount.addFavoritePlayer(updatePlayer);
            if (!SessionManager.getSessionManager().performOp(session, session::update, userAccount)) {
                userAccount.removeFavoritePlayer(updatePlayer);
                return;
            }
        }
        System.out.println(player.getName() + " has been added to your favorite players list.");
    }

    @Override
    public void displayPage() {
        System.out.println(player + "\n");
        System.out.println("To add this player to your list of favorite players, please type \'" +
                ADD_FAVORITE_PLAYER_ID + "\'.");
    }

}
