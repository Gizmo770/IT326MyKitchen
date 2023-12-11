package com.it326.mykitchenresources.serviceTests;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.it326.mykitchenresources.dbs.IngredientDb;
import com.it326.mykitchenresources.dbs.ShoppingListDb;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;
import com.it326.mykitchenresources.entities.ShoppingListIngredient;
import com.it326.mykitchenresources.services.ShoppingListService;

public class ShoppingListServiceTest {

    @Mock
    private ShoppingListDb shoppingListDb;

    @Mock
    private IngredientDb ingredientDb; // Mocking the Ingredient repository.

    private ShoppingListService shoppingListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingListService = new ShoppingListService(shoppingListDb, ingredientDb);
    }

    @Test
    public void testAddIngredientToShoppingList() {
        // Arrange
        Integer shoppingListId = 1;
        Integer ingredientId = 1;
        Double quantity = 2.0;
        ShoppingList shoppingList = new ShoppingList();
        Ingredient ingredient = new Ingredient("Tomato", 1.0, null);
        
        Mockito.when(shoppingListDb.findById(shoppingListId.longValue())).thenReturn(Optional.of(shoppingList));
        Mockito.when(ingredientDb.findById(ingredientId.longValue())).thenReturn(Optional.of(ingredient));

        // Act
        shoppingListService.addIngredientToShoppingList(shoppingListId, ingredientId, quantity);

        // Assert
        assertEquals(1, shoppingList.getIngredientsInShoppingList().size());
        assertEquals(quantity, shoppingList.getIngredientsInShoppingList().get(0).getIngredient().getQuantity());

        // Verify that save method was called with the updated shopping list
        Mockito.verify(shoppingListDb).save(shoppingList);
    }

    @Test
    public void testDeleteIngredientFromShoppingList() {
    // Arrange
    Long shoppingListId = 1L;
    Long ingredientId = 1L;
    ShoppingList shoppingList = new ShoppingList();
    shoppingList.setIngredientsInShoppingList(new ArrayList<>());
    Ingredient ingredient = new Ingredient();
    ingredient.setIngredientId(ingredientId);
    ShoppingListIngredient shoppingListIngredient = new ShoppingListIngredient();
    shoppingListIngredient.setIngredient(ingredient);
    shoppingList.getIngredientsInShoppingList().add(shoppingListIngredient);
    
    Mockito.when(shoppingListDb.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
    
    // Act
    shoppingListService.deleteShoppingListIngredient(shoppingListId, ingredientId);

    // Assert
    assertTrue(shoppingList.getIngredientsInShoppingList().isEmpty());

    // Verify that save method was called with the updated shopping list
    Mockito.verify(shoppingListDb).save(shoppingList);
}

    // Additional test methods as needed...
}
