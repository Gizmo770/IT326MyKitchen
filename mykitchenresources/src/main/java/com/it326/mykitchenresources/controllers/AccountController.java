package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.services.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/hello-account")
    public String hello() {
        return "Hello, world!";
    }

    @PostMapping("/new-account")
    public ResponseEntity<String> createAccount(String name, String username, String password) {
        System.out.println("Creating account...");

        try {
            accountService.createAccount(name, username, password);
            return ResponseEntity.ok("Account created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account");
        }
    }
}