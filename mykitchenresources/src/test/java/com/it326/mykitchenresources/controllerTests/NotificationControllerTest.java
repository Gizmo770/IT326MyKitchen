package com.it326.mykitchenresources.controllerTests;

import com.it326.mykitchenresources.controllers.NotificationController;
import com.it326.mykitchenresources.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEmailShoppingList_Success() {
        // Arrange
        Integer accountId = 1;
        String emailToSendTo = "test@example.com";

        // Act
        ResponseEntity<String> response = notificationController.emailShoppingList(accountId, emailToSendTo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shopping list emailed successfully", response.getBody());
        verify(emailService, times(1)).emailShoppingList(accountId, emailToSendTo);
    }

    @Test
    public void testEmailShoppingList_Error() {
        // Arrange
        Integer accountId = 1;
        String emailToSendTo = "test@example.com";
        doThrow(new RuntimeException()).when(emailService).emailShoppingList(accountId, emailToSendTo);

        // Act
        ResponseEntity<String> response = notificationController.emailShoppingList(accountId, emailToSendTo);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error emailing shopping list", response.getBody());
        verify(emailService, times(1)).emailShoppingList(accountId, emailToSendTo);
    }

    @Test
    public void testTextShoppingList_Success() {
        // Arrange
        Integer accountId = 1;
        String phoneNumberToSendTo = "1234567890";
        String recipientPhoneCarrier = "Verizon";

        // Act
        ResponseEntity<String> response = notificationController.textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shopping list texted successfully", response.getBody());
        verify(emailService, times(1)).textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);
    }

    @Test
    public void testTextShoppingList_Error() {
        // Arrange
        Integer accountId = 1;
        String phoneNumberToSendTo = "1234567890";
        String recipientPhoneCarrier = "Verizon";
        doThrow(new RuntimeException()).when(emailService).textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);

        // Act
        ResponseEntity<String> response = notificationController.textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error texting shopping list", response.getBody());
        verify(emailService, times(1)).textShoppingList(accountId, phoneNumberToSendTo, recipientPhoneCarrier);
    }
}