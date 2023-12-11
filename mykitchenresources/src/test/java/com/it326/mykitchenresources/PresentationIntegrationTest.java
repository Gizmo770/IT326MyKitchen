package com.it326.mykitchenresources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.it326.mykitchenresources.services.EmailService;

@SpringBootTest
public class PresentationIntegrationTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testNotifyOfExpiredIngredients() {
        emailService.notifyOfExpiredIngredients();
        // Add assertions here if necessary
    }

    @Test
    public void testNotifyOfLowIngredients() {
        emailService.notifyOfLowIngredients();
        // Add assertions here if necessary
    }
}