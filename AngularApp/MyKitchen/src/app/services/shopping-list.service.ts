import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../models/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {
  private apiUrl = 'http://your-backend-api/shopping-list'; // Replace with your actual API URL

  constructor(private http: HttpClient) {}

  getShoppingList(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.apiUrl);
  }

  addIngredient(name: string, quantity: number): Observable<Ingredient> {
    const newIngredient = { name, quantity };
    return this.http.post<Ingredient>(this.apiUrl, newIngredient);
  }

  removeIngredient(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}