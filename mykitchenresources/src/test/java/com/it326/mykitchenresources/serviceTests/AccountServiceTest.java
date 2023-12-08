package com.it326.mykitchenresources.serviceTests;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.it326.mykitchenresources.dbs.AccountDb;
import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.services.AccountService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class AccountServiceTest {

    @Mock
    private AccountDb accountDb;
    
    private AccountService accountService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountDb);    
    }

    @Test
    public void testUpdateAccount() {
        // Arrange
        Account existingAccount = new Account();
        existingAccount.setAccountId(1);
        existingAccount.setName("John Doe");
        existingAccount.setUserName("johndoe");
        existingAccount.setHashedPassword("password");
        existingAccount.setEmail("test@example.com");
        existingAccount.setPhoneNumber("1111111111");
        existingAccount.setPhoneCarrier("Verizon");
        existingAccount.setLowIngredientThreshold(10.0);

        // Mock the behavior of accountDb to return the existing account when findByAccountId is called
        Mockito.when(accountDb.findByAccountId(1)).thenReturn(existingAccount);
        // Act
        // Call the method to test
        Account updatedAccount = accountService.updateAccount(
            1, "Jane Smith", "janedoe", 
            "newpassword", "janesmith@example.com", 
            "1234567890", "AT&T", 
            5.0);

        // Assert
        // Verify the returned account has the updated values
        assertEquals("Jane Smith", updatedAccount.getName());
        assertEquals("janedoe", updatedAccount.getUserName());
        assertTrue(BCrypt.checkpw("newpassword", updatedAccount.getHashedPassword()));
        assertEquals("janesmith@example.com", updatedAccount.getEmail());
        assertEquals("1234567890", updatedAccount.getPhoneNumber());
        assertEquals("AT&T", updatedAccount.getPhoneCarrier());
        assertEquals(5.0, updatedAccount.getLowIngredientThreshold());

        // Verify that save method was called with the updated account
        Mockito.verify(accountDb).save(updatedAccount);
    }
}