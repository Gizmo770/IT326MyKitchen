package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.AccountDTO;
import com.it326.mykitchenresources.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @PostMapping("/create")
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