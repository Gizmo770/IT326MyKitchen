package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.services.EmailService;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/hello-notifications")
    public String helloNotifications() {
        return "Hello, notifications!";
    }

    public ResponseEntity<String> emailShoppingList(Integer accountId, String emailToSendTo) {
        System.out.println("Emailing shopping list to " + emailToSendTo + "...");

        try {
            emailService.emailShoppingList(accountId, emailToSendTo);
            return ResponseEntity.ok("Shopping list emailed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error emailing shopping list");
        }
    }

    public ResponseEntity<String> textShoppingList(Integer accountId, String phoneNumberToSendTo, String recipientPhoneCarrier) {
        System.out.println("Texting shopping list to " + phoneNumberToSendTo + "...");

        try {
            emailService.textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);
            return ResponseEntity.ok("Shopping list texted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error texting shopping list");
        }
    }

    public ResponseEntity<String> notifyOfExpiredIngredientsTest() {
        System.out.println("Notifying of expired ingredients...");

        try {
            emailService.notifyOfExpiredIngredients();
            return ResponseEntity.ok("Notified of expired ingredients successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error notifying of expired ingredients");
        }
    }

    public ResponseEntity<String> notifyOfLowIngredientsTest() {
        System.out.println("Notifying of low ingredients...");

        try {
            emailService.notifyOfLowIngredients();
            return ResponseEntity.ok("Notified of low ingredients successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error notifying of low ingredients");
        }
    }
}
