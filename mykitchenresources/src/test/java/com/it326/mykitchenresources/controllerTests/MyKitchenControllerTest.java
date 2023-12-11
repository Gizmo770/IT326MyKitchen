package com.it326.mykitchenresources.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.it326.mykitchenresources.controllers.AccountController;
import com.it326.mykitchenresources.services.EmailService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyKitchenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private AccountController accountController;

    @Test
    public void testEmailShoppingList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/share-list/email")
                .param("currentAccId", "12")
                .param("emailToSendTo", "test@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(emailService, times(1)).emailShoppingList(anyInt(), anyString());
    }

    @Test
    public void testTextShoppingList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/share-list/text")
                .param("currentAccId", "12")
                .param("phoneNumberToSendTo", "test@example.com")
                .param("recipientPhoneCarrier", "AT&T")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(emailService, times(1)).textShoppingList(anyInt(), anyString(), anyString());

    }

    @Test
    public void testUpdateAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/account/update")
                .param("accountId", "1")
                .param("name", "John Doe")
                .param("username", "johndoe")
                .param("password", "password123")
                .param("email", "johndoe@example.com")
                .param("phoneNumber", "1234567890")
                .param("phoneCarrier", "Verizon")
                .param("lowIngredientThreshold", "10.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(accountController, times(1)).updateAccount(
            anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyDouble());
    }

}