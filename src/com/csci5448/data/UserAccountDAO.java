package com.csci5448.data;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Player;
import com.csci5448.content.Sport;
import com.csci5448.content.Team;

import java.util.List;

public class UserAccountDAO implements AccountDAO {

    @Override
    public boolean createAccount(String username, String password) {
        //TODO
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) {
        //TODO
        return false;
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        //TODO
        return false;
    }

    @Override
    public Account getAccount(String username) {
        //TODO
        return null;
    }

    public boolean addFavoriteTeam(UserAccount account, Team team) {
        //TODO
        return false;
    }

    public boolean deleteFavoriteTeam(UserAccount account, Team team) {
        //TODO
        return false;
    }

    public boolean addFavoritePlayer(UserAccount account, Player player) {
        //TODO
        return false;
    }

    public boolean deleteFavoritePlayer(UserAccount account, Player player) {
        //TODO
        return false;
    }

    public List<Team> getFavoriteTeams(UserAccount account, Sport sport) {
        //TODO
        return null;
    }

    public List<Player> getFavoritePlayers(UserAccount account, Sport sport) {
        //TODO
        return null;
    }

}
