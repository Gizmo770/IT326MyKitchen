import { Component } from '@angular/core';

import { RecipeService } from 'src/app/services/recipe.service';
import { RecipeDetails } from 'src/app/models/recipe-details';

import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';


@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.scss']
})
export class RecipeSearchComponent {
  searchString: string = '';
  searchControl = new FormControl();
  calorieControl = new FormControl();
  recipes: RecipeDetails[] = [];

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.searchControl.valueChanges.pipe(
      debounceTime(400),
      distinctUntilChanged(),
      switchMap((searchString: string) => this.recipeService.searchRecipeByString(searchString))
    ).subscribe(
      (response: RecipeDetails[]) => {
        this.recipes = response;
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.calorieControl.valueChanges.pipe(
      debounceTime(400),
      distinctUntilChanged()
    ).subscribe(
      (maxCalories: number) => {
        this.recipes = this.recipeService.filterRecipesByCalories(this.recipes, maxCalories);
      },
      (error: any) => {
        console.log(error);
      }
    );
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
