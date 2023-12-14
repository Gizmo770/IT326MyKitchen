import { Injectable } from '@angular/core';

import { HttpClient, HttpParams } from '@angular/common/http';

import { AccountService } from './account.service';
import { Ingredient, ShoppingList, ShoppingListIngredient } from '../models/account';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {

  private getListItemsUrl: string;
  private updateListItemsUrl: string;
  private getFridgeIngUrl: string;
  private setFridgeIngUrl: string;
  private listItems: ShoppingListIngredient[];
  private ingredients: Ingredient[];

  constructor(
    private http: HttpClient,
    private snackBar: MatSnackBar,
    private accountService: AccountService
  ) {
    this.getListItemsUrl = 'http://localhost:8081/shopping-list/get-list';
    this.updateListItemsUrl = 'http://localhost:8081/shopping-list/update-list';
    this.getFridgeIngUrl = 'http://localhost:8081/fridge/get-ingredients';
    this.setFridgeIngUrl = 'http://localhost:8081/fridge/update-ingredients';

    this.listItems = [];
    this.ingredients = [];

    if(this.accountService.getCurrentAccount()) {
      this.getFridgeIngredients();
      this.getListItems();
    }
  }

  ngOnInit(): void {
    if(this.accountService.getCurrentAccount()) {
      this.getFridgeIngredients();
      this.getListItems();
    }
  }

  getFridgeIngredients(): Observable<Ingredient[]> {
    return new Observable<Ingredient[]>(observer => {
      const currentAccount = this.accountService.getCurrentAccount();
      if (currentAccount) {
        const accountId = currentAccount.accountId ? currentAccount.accountId.toString() : '';
        this.http.get<Ingredient[]>(this.getFridgeIngUrl, {
          params: new HttpParams().set('currentAccId', accountId)
        }).subscribe(ingredients => {
          this.ingredients = ingredients;
          console.log(this.ingredients);
          observer.next(this.ingredients);
        });
      } else {
        observer.next([]);
      }
    });
  }

  setFridgeIngredients(ingredients: Ingredient[]): void {
    const currentAccount = this.accountService.getCurrentAccount();
    if (currentAccount && currentAccount.accountId) {
      this.http.post(this.setFridgeIngUrl, ingredients, {
        params: new HttpParams().set('currentAccId', currentAccount.accountId.toString()),
        responseType: 'text'
      }).subscribe(() => {
        this.snackBar.open('Ingredients updated successfully', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
        });
      }, error => {
        this.snackBar.open('An error occurred: ' + error.message, 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
        });
      });
    }
  }

  getListItems(): Observable<ShoppingListIngredient[]> {
    return new Observable<ShoppingListIngredient[]>(observer => {
      const currentAccount = this.accountService.getCurrentAccount();
      if (currentAccount) {
        const accountId = currentAccount.accountId ? currentAccount.accountId.toString() : '';
        this.http.get<ShoppingListIngredient[]>(this.getListItemsUrl, {
          params: new HttpParams().set('currentAccId', accountId)
        }).subscribe(listItems => {
          this.listItems = listItems;
          console.log(this.listItems);
          observer.next(this.listItems);
        });
      } else {
        observer.next([]);
      }
    });
  }

  saveListItems(listItems: ShoppingListIngredient[]): void {
    const currentAccount = this.accountService.getCurrentAccount();
    console.log("listItems: ", listItems);

    if (currentAccount && currentAccount.accountId) {
      this.http.post(this.updateListItemsUrl, JSON.stringify(listItems), {
        headers: { 'Content-Type': 'application/json' },
        params: new HttpParams().set('currentAccId', currentAccount.accountId.toString()),
        responseType: 'text'
      }).subscribe(() => {
        this.snackBar.open('Shopping list updated successfully', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
        });
      }, error => {
        this.snackBar.open('An error occurred: ' + error.message, 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
        });
      });
    }
  }
}
