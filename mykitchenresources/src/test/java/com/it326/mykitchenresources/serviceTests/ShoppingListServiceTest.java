package com.it326.mykitchenresources.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import com.it326.mykitchenresources.entities.*;
import com.it326.mykitchenresources.repositories.*;
import com.it326.mykitchenresources.services.ShoppingListService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private ShoppingListIngredientRepository shoppingListIngredientRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private ShoppingListService shoppingListService;

    private Account mockAccount;
    private ShoppingList mockShoppingList;
    private Ingredient mockIngredient;
    private ShoppingListIngredient mockShoppingListIngredient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Create mock objects for the test
        mockAccount = new Account();
        mockAccount.setAccountId(1);
        
        mockShoppingList = new ShoppingList();
        mockShoppingList.setAccount(mockAccount);
        mockShoppingList.setListId(1);
        
        mockIngredient = new Ingredient();
        mockIngredient.setId(1);
        mockIngredient.setName("Tomato");
        
        mockShoppingListIngredient = new ShoppingListIngredient();
        mockShoppingListIngredient.setShoppingList(mockShoppingList);
        mockShoppingListIngredient.setIngredient(mockIngredient);
        mockShoppingListIngredient.setPriority(1);
    }

    @Test
    public void testAddIngredientToShoppingList() {
        when(shoppingListRepository.findById(any())).thenReturn(Optional.of(mockShoppingList));
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(mockIngredient));
        when(shoppingListIngredientRepository.save(any(ShoppingListIngredient.class))).thenReturn(mockShoppingListIngredient);

        ShoppingListIngredient addedIngredient = shoppingListService.addIngredientToShoppingList(mockShoppingList.getListId(), mockIngredient.getId(), 1);

        assertNotNull(addedIngredient);
        assertEquals(mockIngredient.getId(), addedIngredient.getIngredient().getId());
        assertEquals(1, addedIngredient.getPriority());
    }

    @Test
    public void testRemoveIngredientFromShoppingList() {
        // Assume that the ingredient is already in the shopping list
        shoppingListService.removeIngredientFromShoppingList(mockShoppingList.getListId(), mockIngredient.getId());
        verify(shoppingListIngredientRepository).deleteByShoppingListIdAndIngredientId(mockShoppingList.getListId(), mockIngredient.getId());
    }

    @Test
    public void testSearchShoppingListIngredients() {
        when(shoppingListIngredientRepository.findAllByShoppingListId(any())).thenReturn(Collections.singletonList(mockShoppingListIngredient));
        List<Ingredient> foundIngredients = shoppingListService.searchShoppingListIngredients(mockShoppingList.getListId(), "tomato");
        
        assertFalse(foundIngredients.isEmpty());
        assertTrue(foundIngredients.stream().anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase("Tomato")));
    }

    @Test
    public void testPrioritizeIngredientsInShoppingList() {
        // Given an existing ingredient in the shopping list
        ShoppingListIngredientId id = new ShoppingListIngredientId(mockShoppingList.getListId(), mockIngredient.getId());
        when(shoppingListIngredientRepository.findById(id)).thenReturn(Optional.of(mockShoppingListIngredient));

        // When the priority is updated
        int newPriority = 2;
        shoppingListService.prioritizeIngredientInShoppingList(mockShoppingList.getListId(), mockIngredient.getId(), newPriority);

        // Then the repository's save method should be called with an ingredient having updated priority
        assertEquals(newPriority, mockShoppingListIngredient.getPriority(), "The priority should be updated to the new value.");
        verify(shoppingListIngredientRepository).save(mockShoppingListIngredient);
    }

    

    @Test
    public void testTrackSpendingBudgetInShoppingList() {
        when(shoppingListRepository.findById(any())).thenReturn(Optional.of(mockShoppingList));
        
        shoppingListService.setShoppingListBudget(mockShoppingList.getListId(), 50.0);
        verify(shoppingListRepository).save(mockShoppingList);
        assertEquals(50.0, mockShoppingList.getBudget());
        
        Double budget = shoppingListService.getShoppingListBudget(mockShoppingList.getListId());
        assertEquals(50.0, budget);
    }

    // Other helper methods to support your tests
}