package com.csci5448.pages.user_pages;

import com.csci5448.content.Player;
import com.csci5448.pages.Page;

public class PlayerPage extends Page {

    private final Player player;

    public PlayerPage(Player player) {
        this.player = player;
    }

    public void displayPage() {
        System.out.println("Welcome to the Player Page for " + player.getName() + "!");
    }

}
