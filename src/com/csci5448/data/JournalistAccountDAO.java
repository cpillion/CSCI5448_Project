package com.csci5448.data;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;

public class JournalistAccountDAO implements AccountDAO {

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

    public boolean verifyProfession(JournalistAccount account) {
        //TODO
        return false;
    }
}
