import { Component } from '@angular/core';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.scss'],
})
export class RecipeSearchComponent {
  // Add properties and methods for recipe search
  searchTerm: string = '';

  searchRecipes() {
    // Implement recipe search logic here using the searchTerm
    console.log('Searching recipes for:', this.searchTerm);
  }
}

