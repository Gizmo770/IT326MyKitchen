package com.it326.mykitchenresources.services;

import java.util.ArrayList;
import java.util.Calendar;
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



@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private FridgeService fridgeService;

    @Autowired 
    private AccountService accountService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Scheduled(cron = "0 0 12 * * ?")
    public void notifyOfExpiredIngredients() {

        Calendar weekFromNowCalendar = Calendar.getInstance();
        weekFromNowCalendar.add(Calendar.DAY_OF_MONTH, 7);

        for (Account account : accountService.findAll()) {
            List<Ingredient> expIngredients = new ArrayList<Ingredient>();
            Fridge fridge = fridgeService.getFridgeByAccountId(account.getAccountId());
            for (Ingredient ingredient : fridge.getIngredients()) {
                if (ingredient.getExpirationDate() != null) {
                    if(ingredient.getExpirationDate().before(weekFromNowCalendar.getTime())) {
                        expIngredients.add(ingredient);
                    }
                }
            }
            
            if (!expIngredients.isEmpty()) {
                String phoneNumber = account.getPhoneNumber();
                String email = account.getEmail();
                if(email != null) {
                    SimpleMailMessage message = buildMessage(expIngredients, email);
                    emailSender.send(message);
                }
                if(phoneNumber != null) {
                    phoneNumber = phoneNumber + phoneCarrierToEmail(account.getPhoneCarrier());
                    SimpleMailMessage message = buildMessage(expIngredients, phoneNumber);
                    emailSender.send(message);
                }
            }
        }               
    }

    private SimpleMailMessage buildMessage(List<Ingredient> expIngredients, String sendTo) {

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
                return "";
        }
    }
}
