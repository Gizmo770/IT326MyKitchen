import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { Ingredient } from 'src/app/models/account';

import { IngredientsService } from 'src/app/services/ingredients.service';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.scss'],
})
export class FridgeComponent implements OnInit {
  @ViewChild('dt', { static: false }) dt!: Table;

  ingredients: Ingredient[];

  constructor(private ingredientService: IngredientsService) {
    this.ingredients = [];
  }

  ngOnInit() {
    this.getIngredients();
  }

  getIngredients(): void {
    this.ingredientService.getFridgeIngredients().subscribe(ingredients => {
      this.ingredients = ingredients;
    });
  }

  addIngredient(): void {
    // Create a new ingredient with default values
    const newIngredient: Ingredient = {
      ingredientId: 0, // This should be replaced with a real ID
      name: '',
      quantity: 0
    };

    // Add the new ingredient to the array
    this.ingredients.push(newIngredient);
  }

  deleteIngredient(ingredient: Ingredient): void {
    // Find the index of the ingredient in the array
    const index = this.ingredients.indexOf(ingredient);

    // If the ingredient is found, remove it from the array
    if (index !== -1) {
      this.ingredients.splice(index, 1);
    }
  }

  updateIngredient(ingredient: Ingredient): void {
    // Implement your logic to update the ingredient here.
    // This could involve calling an API to update the ingredient on the server.
  }

  saveFridgeIngredients(): void {
    this.ingredientService.setFridgeIngredients(this.ingredients);
  }

}
