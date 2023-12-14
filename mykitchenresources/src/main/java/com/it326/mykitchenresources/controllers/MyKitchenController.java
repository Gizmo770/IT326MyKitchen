package com.it326.mykitchenresources.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;
import com.it326.mykitchenresources.entities.ShoppingListIngredient;
import com.it326.mykitchenresources.entities.recipe.RecipeDetails;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class MyKitchenController {

    @Autowired
    private AccountController accountController;

    @Autowired
    private ShoppingListController shoppingListController;

    @Autowired
    private RecipeController recipeController;

    @Autowired
    private FridgeController fridgeController;

    @Autowired 
    NotificationController notificationController;

    @RequestMapping("/hello-kitchen")
    @ResponseBody
    public String helloKitchen() {
        return "Hello, kitchen!";
    }

    /* ----------------------------
    Account Controller Functions
    ----------------------------- */
    // Works
    @PostMapping("/account/create")
    @ResponseBody
	public ResponseEntity<String> createAccount(
        @RequestParam String name, 
        @RequestParam String username, 
        @RequestParam String password) {
		return accountController.createAccount(name, username, password);
	}

    // Works
    @DeleteMapping("/account/delete")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(
        @RequestParam Integer accountId) {
        return accountController.deleteAccount(accountId);
    }

    @PostMapping("/account/login")
    @ResponseBody
    public ResponseEntity<Account> login(
        @RequestParam String username, 
        @RequestParam String password) {
        return accountController.login(username, password);
    }

    @PostMapping("/account/update")
    @ResponseBody
    public ResponseEntity<Account> updateAccount(
        @RequestParam Integer accountId,
        @RequestParam String name,
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String email,
        @RequestParam String phoneNumber,
        @RequestParam String phoneCarrier,
        @RequestParam Double lowIngredientThreshold) {
        return accountController.updateAccount(accountId, name, username, password, email, phoneNumber, phoneCarrier, lowIngredientThreshold);
    }

    /* ----------------------------------
    Shopping List Controller Functions
    ----------------------------------- */
    // Works
    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(
        @RequestParam Integer currentAccId) {
        return shoppingListController.createShoppingList(currentAccId);
    }

    @RequestMapping("/shopping-list/get-list")
    @ResponseBody
    public ResponseEntity<List<ShoppingListIngredient>> getListItems(
        @RequestParam Integer currentAccId) {
        return shoppingListController.getListItems(currentAccId);
    }

    @PostMapping("/shopping-list/update-list")
    @ResponseBody
    public ResponseEntity<String> updateShoppingListIngredients(
        @RequestParam Integer currentAccId,
        @RequestBody List<ShoppingListIngredient> listItems) {
        return shoppingListController.updateShoppingListIngredients(currentAccId, listItems);
    }

    /* --------------------------
    Fridge Controller Functions
    --------------------------- */
    // Works
    @PostMapping("/fridge/create")
    @ResponseBody
    public ResponseEntity<String> createFridge(
        @RequestParam Integer currentAccId) {
        return fridgeController.createFridge(currentAccId);
    }

    // Works
    @PostMapping("/fridge/add-ingredient")
    @ResponseBody
    public ResponseEntity<String> addIngredientToFridge(
        @RequestParam Integer currentAccId,
        @RequestParam String ingName,
        @RequestParam(required = false) Optional<Double> ingQuantity,
        @RequestParam(required = false) Optional<String> ingExpDate) {
        return fridgeController.addIngredientToFridge(currentAccId, ingName, ingQuantity, ingExpDate);
    }

    @RequestMapping("/fridge/get-ingredients")
    @ResponseBody
    public ResponseEntity<List<Ingredient>> getFridgeIngredients(
        @RequestParam Integer currentAccId) {
        return fridgeController.getFridgeIngredients(currentAccId);
    }

    @PostMapping("/fridge/update-ingredients")
    @ResponseBody
    public ResponseEntity<String> updateFridgeIngredients(
        @RequestParam Integer currentAccId,
        @RequestBody List<Ingredient> listIngredients) {
        return fridgeController.updateFridgeIngredients(currentAccId, listIngredients);
    }


    /* --------------------------
    Recipe Controller Functions
    --------------------------- */
    @RequestMapping("/recipe/fridge/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByFridge(
        @RequestParam Integer currentAccId) {
        return recipeController.searchRecipesByFridge(currentAccId);
    }

    // Works
    @RequestMapping("/recipe/string/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByString(
        @RequestParam String ingredients) {
        return recipeController.searchRecipesByString(ingredients);
    }

    /* ----------------------------------
    Email And Notify Controller Functions
    ----------------------------------- */
    @PostMapping("/share-list/email")
    @ResponseBody
    public ResponseEntity<String> emailShoppingList(
        @RequestParam Integer currentAccId,
        @RequestParam String emailToSendTo) {
        return notificationController.emailShoppingList(currentAccId, emailToSendTo);
    }

    @PostMapping("/share-list/text")
    @ResponseBody
    public ResponseEntity<String> textShoppingList(
        @RequestParam Integer currentAccId,
        @RequestParam String phoneNumberToSendTo,
        @RequestParam String recipientPhoneCarrier) {
        return notificationController.textShoppingList(currentAccId, phoneNumberToSendTo, recipientPhoneCarrier);
    }

    @PostMapping("/notify/expired")
    @ResponseBody
    public ResponseEntity<String> notifyOfExpiredIngredientsTest() {
        return notificationController.notifyOfExpiredIngredientsTest();
    }

    @PostMapping("/notify/low")
    @ResponseBody
    public ResponseEntity<String> notifyOfLowIngredientsTest() {
        return notificationController.notifyOfLowIngredientsTest();
    }
}
