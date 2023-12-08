package com.it326.mykitchenresources.serviceTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.services.AccountService;
import com.it326.mykitchenresources.services.EmailService;
import com.it326.mykitchenresources.services.FridgeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private FridgeService fridgeService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private Account account;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        
        account = new Account();
        account.setAccountId(1);
        account.setEmail("test@example.com");
        account.setPhoneNumber("1234567890");
        account.setPhoneCarrier("AT&T");
        account.setLowIngredientThreshold(1);
    }

    //A test that verifies that the email sends
    //when the fridge has expired ingredients
    @Test
    public void testNotifyOfExpiredIngredients() {
        Fridge fridge = new Fridge();
        fridge.setIngredients(new ArrayList<>());

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setExpirationDate(Calendar.getInstance().getTime());
        fridge.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setExpirationDate(Calendar.getInstance().getTime());
        fridge.getIngredients().add(ingredient2);

        when(accountService.findAll()).thenReturn(Collections.singletonList(account));
        when(fridgeService.getFridgeByAccountId(account.getAccountId())).thenReturn(fridge);

        // Call the method
        emailService.notifyOfExpiredIngredients();

        // Verify the email sender
        verify(emailSender, times(2)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testNotifyOfLowIngredients() {
        Fridge fridge = new Fridge();
        fridge.setIngredients(new ArrayList<>());

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setQuantity(1);
        fridge.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setQuantity(1);
        fridge.getIngredients().add(ingredient2);

        when(accountService.findAll()).thenReturn(Collections.singletonList(account));
        when(fridgeService.getFridgeByAccountId(account.getAccountId())).thenReturn(fridge);

        // Call the method
        emailService.notifyOfLowIngredients();

        // Verify the email sender to send to text and email
        verify(emailSender, times(2)).send(any(SimpleMailMessage.class));
    }

    @AfterEach
    public void tearDown()
    {
        
    }
}