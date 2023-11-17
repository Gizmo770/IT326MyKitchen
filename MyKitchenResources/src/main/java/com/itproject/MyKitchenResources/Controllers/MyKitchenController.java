package com.itproject.MyKitchenResources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.itproject.MyKitchenResources.Entities.AccountDTO;

@RestController
public class MyKitchenController {

    private final AccountController accountController;

    @Autowired
    public MyKitchenController(AccountController accountController) {
        this.accountController = accountController;
    }

    @PostMapping("/createAccount")
    @ResponseBody
	public ResponseEntity<String> createAccount(
		@RequestBody AccountDTO accountDTO) {
		return accountController.createAccount(accountDTO);
	}
}