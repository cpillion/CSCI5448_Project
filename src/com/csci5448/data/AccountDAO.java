package com.csci5448.data;

import com.csci5448.accounts.Account;

public interface AccountDAO {

    boolean createAccount(String username, String password);
    boolean deleteAccount(Account account);
    boolean verifyPassword(String username, String password);
    Account getAccount(String username);

}
