package com.it326.mykitchenresources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO accountDTO) {
        // Retrieve values from the DTO (Data Transfer Object)
        String name = accountDTO.getName();
        String username = accountDTO.getUsername();
        String password = accountDTO.getPassword();

        // Create the account
        Account createdAccount = accountService.createAccount(name, username, password);

        if (createdAccount != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create account");
        }
    }
}