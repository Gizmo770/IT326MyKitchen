import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';

import { Ingredient, ShoppingListIngredient } from 'src/app/models/account';

import { ShareListComponent } from '../share-list/share-list.component';
import { IngredientsService } from 'src/app/services/ingredients.service';

@Component({
  selector: 'app-shoplist',
  templateUrl: './shoplist.component.html',
  styleUrl: './shoplist.component.scss'
})
export class ShoplistComponent implements OnInit {
  @ViewChild('dt', { static: false }) dt!: Table;

  shoppingListItems: ShoppingListIngredient[];

  constructor(private ingredientService: IngredientsService) {
    this.shoppingListItems = [];
  }

  ngOnInit() {
    this.getListItems();
  }

  getListItems(): void {
    this.ingredientService.getListItems().subscribe(listItems => {
      this.shoppingListItems = listItems;
    });
  }

  addListItem(): void {
    // Create a new ingredient with default values
    const newIngredient: Ingredient = {
      ingredientId: 0, // replace with actual values if available
      name: '',
      quantity: 0,
      expirationDate: new Date()
    };

    const newListItem: ShoppingListIngredient = {
      shoppingList: undefined, // or new ShoppingList() if you have a constructor for ShoppingList
      ingredient: newIngredient,
      priority: 0
    };

    // Add the new item to your shopping list items
    this.shoppingListItems.push(newListItem);
  }

  updateListItem(listItem: ShoppingListIngredient): void {
    // Implement your logic to update the listItem here.
    // This could involve calling an API to update the listItem on the server.
  }

  deleteListItem(listItem: ShoppingListIngredient): void {
    // Find the index of the listItem in the array
    const index = this.shoppingListItems.indexOf(listItem);

    // If the listItem is found, remove it from the array
    if (index !== -1) {
      this.shoppingListItems.splice(index, 1);
    }
  }

  saveListItems(): void {
    this.ingredientService.saveListItems(this.shoppingListItems);
  }
}
