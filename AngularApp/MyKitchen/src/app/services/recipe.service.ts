import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RecipeDetails } from '../models/recipe-details';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private searchRecipeUrl: string;

  constructor(private http: HttpClient) {
    this.searchRecipeUrl = 'http://localhost:8081/recipe/string/search';
  }

  public searchRecipeByString(searchString: string): Observable<RecipeDetails[]> {
    const params = new HttpParams().set('ingredients', searchString);
    return this.http.get<RecipeDetails[]>(this.searchRecipeUrl, { params });
  }

  public filterRecipesByCalories(recipes: RecipeDetails[], maxCalories: number): RecipeDetails[] {
    return recipes.filter(recipe => recipe.calories !== undefined && recipe.calories <= maxCalories);
  }
}
