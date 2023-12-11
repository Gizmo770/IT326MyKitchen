package com.it326.mykitchenresources.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.it326.mykitchenresources.dbs.IngredientDb;
import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.services.AccountService;
import com.it326.mykitchenresources.services.EmailService;
import com.it326.mykitchenresources.services.FridgeService;
import com.it326.mykitchenresources.services.ShoppingListService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private FridgeService fridgeService;

    @Mock
    private IngredientDb ingredientRepository;

    @Mock
    private ShoppingListService shoppingListService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private Account account;

    @Mock
    private Fridge fridge;

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

    @Test
    public void testEmailShoppingList() {
        //Arrange
        Account testAccount = new Account(1, "John Doe", "jdoe", "password");
        Ingredient testIng = new Ingredient();
        testIng.setName("TestIng");
        testIng.setQuantity(1);
        testIng.setExpirationDate(Calendar.getInstance().getTime());
        List<Ingredient> testIngredients = new ArrayList<Ingredient>();
        testIngredients.add(testIng);

        when(accountService.findByAccountId(1)).thenReturn(testAccount);
        when(shoppingListService.getIngredientsInShoppingList(1))
            .thenReturn(testIngredients);
    

        //Act
        emailService.emailShoppingList(1, "test@example.com");

        //Assert
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testTextShoppingList() {
        //Arrange
        Account testAccount = new Account(1, "John Doe", "jdoe", "password");
        Ingredient testIng = new Ingredient();
        testIng.setName("TestIng");
        testIng.setQuantity(1);
        testIng.setExpirationDate(Calendar.getInstance().getTime());
        List<Ingredient> testIngredients = new ArrayList<Ingredient>();
        testIngredients.add(testIng);

        when(accountService.findByAccountId(1)).thenReturn(testAccount);
    

        //Act
        emailService.textShoppingList(1, "1234567890", "AT&T");

        //Assert
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}