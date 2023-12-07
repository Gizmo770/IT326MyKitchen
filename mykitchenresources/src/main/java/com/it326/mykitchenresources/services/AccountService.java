package com.it326.mykitchenresources.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.AccountDb;
import com.it326.mykitchenresources.entities.Account;

@Service
public class AccountService {

    @Autowired
    private AccountDb accountDb;

    public void createAccount(String name, String username, String password) {
        Account account = new Account();
        account.setName(name);
        account.setUserName(username);
        account.setHashedPassword(hashPassword(password));
        accountDb.save(account);
    }

    public void deleteById(Integer accountId) {
        accountDb.deleteById(accountId);
    }

    public Account login(String username, String password) {
        Account account = accountDb.findByUsername(username);
        if (account != null && BCrypt.checkpw(password, account.getHashedPassword())) {
            return account;
        }
        return null;
    }

    public Account findByAccountId(Integer accountId) {
        return accountDb.findByAccountId(accountId);
    }

    public List<Account> findAll() {
        return accountDb.findAll();
    }

    // Helper function to hash passwords.
    private String hashPassword(String password) {
        // Generate a salt and hash the password using BCrypt
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
