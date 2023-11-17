package com.itproject.MyKitchenResources.Services;

import com.itproject.MyKitchenResources.Repositories.AccountRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.itproject.MyKitchenResources.Entities.Account;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String name, String username, String password) {
        Account account = new Account();
        account.setName(name);
        account.setUserName(username);
        account.setHashedPassword(hashPassword(password));

        return accountRepository.save(account);
    }

        private String hashPassword(String password) {
        // Generate a salt and hash the password using BCrypt
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
