import { Component } from '@angular/core';

import { RecipeService } from 'src/app/services/recipe.service';
import { RecipeDetails } from 'src/app/models/recipe-details';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.scss']
})
export class RecipeSearchComponent {
  searchString: string = '';
  recipes: RecipeDetails[] = [];

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
  }

  public searchRecipeByString(searchString: string): void {
    this.recipeService.searchRecipeByString(searchString).subscribe(
      (response: RecipeDetails[]) => {
        this.recipes = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
