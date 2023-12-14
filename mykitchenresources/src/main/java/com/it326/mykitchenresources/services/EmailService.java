package com.it326.mykitchenresources.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;

import jakarta.transaction.Transactional;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private FridgeService fridgeService;

    @Autowired 
    private AccountService accountService;

    @Autowired
    private ShoppingListService shoppingListService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    public void notifyOfExpiredIngredients() {

        Date currentDate = Calendar.getInstance().getTime();

        Calendar weekFromNowCalendar = Calendar.getInstance();
        weekFromNowCalendar.add(Calendar.DAY_OF_MONTH, 7);
        Date oneWeekFromNow = weekFromNowCalendar.getTime();

        for (Account account : accountService.findAll()) {
            if(account != null) {
                List<Ingredient> expIngredients = new ArrayList<Ingredient>();
                Fridge fridge = fridgeService.getFridgeByAccountId(account.getAccountId());
                for (Ingredient ingredient : fridge.getIngredients()) {
                    if (ingredient.getExpirationDate() != null) {
                        if(ingredient.getExpirationDate().after(currentDate) 
                        || ingredient.getExpirationDate().before(oneWeekFromNow)) {
                            expIngredients.add(ingredient);
                        }
                    }
                }
                
                if (!expIngredients.isEmpty()) {
                    String phoneNumber = account.getPhoneNumber();
                    String email = account.getEmail();
                    if(email != null) {
                        SimpleMailMessage message = buildExpiringMessage(expIngredients, email);
                        emailSender.send(message);
                    }
                    if(phoneNumber != null) {
                        phoneNumber = phoneNumber + phoneCarrierToEmail(account.getPhoneCarrier());
                        SimpleMailMessage message = buildExpiringMessage(expIngredients, phoneNumber);
                        emailSender.send(message);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    public void notifyOfLowIngredients() {
            
        for (Account account : accountService.findAll()) {
            if(account != null) {
                List<Ingredient> lowIngredientsList = new ArrayList<Ingredient>();
                Fridge fridge = fridgeService.getFridgeByAccountId(account.getAccountId());
                for (Ingredient ingredient : fridge.getIngredients()) {
                    if (ingredient != null && !Double.isNaN(ingredient.getQuantity())) {
                        if(ingredient.getQuantity() <= account.getLowIngredientThreshold()) {
                            lowIngredientsList.add(ingredient);
                        }
                    }
                }
                
                if (!lowIngredientsList.isEmpty()) {
                    String phoneNumber = account.getPhoneNumber();
                    String email = account.getEmail();
                    if(email != null) {
                        SimpleMailMessage message = buildLowIngredientMessage(lowIngredientsList, email);
                        emailSender.send(message);
                    }
                    if(phoneNumber != null) {
                        phoneNumber = phoneNumber + phoneCarrierToEmail(account.getPhoneCarrier());
                        SimpleMailMessage message = buildLowIngredientMessage(lowIngredientsList, phoneNumber);
                        emailSender.send(message);
                    }
                }
            }
        }
    }

    public void emailShoppingList(Integer accountId, String emailToSendTo) {

        Account account = accountService.findByAccountId(accountId);
        List<Ingredient> shoppingList = shoppingListService.getIngredientsInShoppingList(accountId);

        SimpleMailMessage message = buildShoppingListMessage(account.getName(), shoppingList, emailToSendTo);
        emailSender.send(message);
        System.out.println("Email sent to " + emailToSendTo);
    }

    public SimpleMailMessage textShoppingList(Integer accountId, String phoneNumberToSendTo, String recipientPhoneCarrier) {

        Account account = accountService.findByAccountId(accountId);
        List<Ingredient> shoppingList = shoppingListService.getIngredientsInShoppingList(accountId);

        String phoneNumber = phoneNumberToSendTo + phoneCarrierToEmail(recipientPhoneCarrier);
        SimpleMailMessage message = buildShoppingListMessage(account.getName(), shoppingList, phoneNumber);
        emailSender.send(message);
        System.out.println("Text sent to " + phoneNumberToSendTo);

        return message;
    }


    /* ----------------------------------
     * Message Builder and Helper Methods
     * ---------------------------------- */

    private SimpleMailMessage buildExpiringMessage(List<Ingredient> expIngredients, String sendTo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(sendTo);
        message.setSubject("myKitchen: Your ingredients are expiring soon!");

        String body = "The following ingredients are expiring soon: \n";
        for (Ingredient ingredient : expIngredients) {
            body += ingredient.getName() + " " + ingredient.getExpirationDate().toString() + "\n";
        }
        message.setText(body);

        return message;
    }

    private SimpleMailMessage buildLowIngredientMessage(List<Ingredient> lowIngredients, String sendTo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(sendTo);
        message.setSubject("myKitchen: Your ingredients are running low!");

        String body = "The following ingredients are running low: \n";
        for (Ingredient ingredient : lowIngredients) {
            body += ingredient.getName() + " " + ingredient.getQuantity() + "\n";
        }
        message.setText(body);

        return message;
    }

    private SimpleMailMessage buildShoppingListMessage(String name, List<Ingredient> shoppingList, String sendTo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(sendTo);
        message.setSubject("myKitchen: " + name + "'s shopping list");

        String body = "Hello " + name + ",\n\nHere is " + name + "'s shopping list: \n\n";
        for (Ingredient ingredient : shoppingList) {
            body += ingredient.getName() + "\n";
        }
        message.setText(body);

        return message;
    }

    private String phoneCarrierToEmail(String phoneCarrier) {
        switch (phoneCarrier) {
            case "AT&T":
                return "@txt.att.net";
            case "Verizon":
                return "@vtext.com";
            case "T-Mobile":
                return "@tmomail.net";
            case "Sprint":
                return "@messaging.sprintpcs.com";
            case "Virgin Mobile":
                return "@vmobl.com";
            case "Tracfone":
                return "@mmst5.tracfone.com";
            case "Metro PCS":
                return "@mymetropcs.com";
            case "Boost Mobile":
                return "@sms.myboostmobile.com";
            case "Cricket":
                return "@sms.cricketwireless.net";
            case "Republic Wireless":
                return "@text.republicwireless.com";
            case "Google Fi":
                return "@msg.fi.google.com";
            case "U.S. Cellular":
                return "@email.us";
            case "Ting":
                return "@message.ting.com";
            case "Consumer Cellular":
                return "@mailmymobile.net";
            case "C-Spire":
                return "@cspire1.com";
            case "Page Plus":
                return "@vtext.com";
            case "Xfinity Mobile":
                return "@vtext.com";
            default:
                return null;
        }
    }
}
