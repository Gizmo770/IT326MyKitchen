package com.it326.mykitchenresources.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.AccountDb;
import com.it326.mykitchenresources.entities.Account;

@Service
public class AccountService {

    @Autowired
    private AccountDb accountDb;

    public Account createAccount(String name, String username, String password) {
        Account account = new Account();
        account.setName(name);
        account.setUserName(username);
        account.setHashedPassword(hashPassword(password));
        return accountDb.save(account);
    }

    public void deleteById(Integer accountId) {
        accountDb.deleteById(accountId);
    }

    public Account findByAccountId(Integer accountId) {
        return accountDb.findByAccountId(accountId);
    }

    // Helper function to hash passwords.
    private String hashPassword(String password) {
        // Generate a salt and hash the password using BCrypt
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
