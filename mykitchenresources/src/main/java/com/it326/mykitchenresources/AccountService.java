package com.it326.mykitchenresources;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.it326.mykitchenresources.Account;

@Service
public class AccountService {

    private final AccountDb accountDb;

    public AccountService(AccountDb accountDb) {
        this.accountDb = accountDb;
    }

    @PostMapping("/createAccount")
    public Account createAccount(String name, String username, String password) {
        Account account = new Account();
        account.setName(name);
        account.setUserName(username);
        account.setHashedPassword(hashPassword(password));
        return accountDb.save(account);
    }

    private String hashPassword(String password) {
        // Generate a salt and hash the password using BCrypt
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
