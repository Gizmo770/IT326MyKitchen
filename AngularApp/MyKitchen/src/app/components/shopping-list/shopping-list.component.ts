import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ShoppingListService } from '../../services/shopping-list.service';
import { Ingredient } from '../../models/ingredient.model';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-shopping-list',
  standalone: true,
  imports: [
    CommonModule, // Import CommonModule for common directives like ngIf, ngFor, etc.
    FormsModule, // Import FormsModule if you're using template-driven forms
    // Add PrimeNG modules that you're using in this component
    ButtonModule,
    InputTextModule,
    // Any other components or modules that ShoppingListComponent depends on
  ],
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit {
  ingredients: Ingredient[] = [];
  newIngredient: Ingredient = new Ingredient(null, '', 0);

  constructor(private shoppingListService: ShoppingListService) { }

  ngOnInit(): void {
    this.loadIngredients();
  }

  loadIngredients() {
    this.shoppingListService.getShoppingList().subscribe(data => {
      this.ingredients = data;
    });
  }

  addIngredient() {
    if (!this.newIngredient.name || this.newIngredient.quantity <= 0) {
      // Handle the error case
      return;
    }
    this.shoppingListService.addIngredient(this.newIngredient.name, this.newIngredient.quantity).subscribe(addedIngredient => {
      this.loadIngredients();
      // Reset the form with a new Ingredient instance
      this.newIngredient = new Ingredient(null, '', 0);
    });
  }

  removeIngredient(id: number | null) {
    if (id === null) {
      // handle error: id is required
      return;
    }
    this.shoppingListService.removeIngredient(id).subscribe(() => {
      this.loadIngredients();
    });
  }
}
