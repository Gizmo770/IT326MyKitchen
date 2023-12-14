import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';

import { RecipeDetails } from '../models/recipe-details';
import { AccountService } from './account.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private searchRecipeStringUrl: string;
  private searchRecipeFridgeUrl: string;

  constructor(
    private http: HttpClient,
    private accountService: AccountService,
    private snackBar: MatSnackBar) {
    this.searchRecipeStringUrl = 'http://localhost:8081/recipe/string/search';
    this.searchRecipeFridgeUrl = 'http://localhost:8081/recipe/fridge/search';
  }

  public searchRecipeByString(searchString: string): Observable<RecipeDetails[]> {
    const params = new HttpParams().set('ingredients', searchString);
    return this.http.get<RecipeDetails[]>(this.searchRecipeStringUrl, { params }).pipe(
      tap(() => this.snackBar.open('Request succeeded', 'Close', { duration: 3000, verticalPosition: 'top', horizontalPosition: 'end' })),
      catchError(error => {
        this.snackBar.open('Request failed', 'Close', { duration: 3000, verticalPosition: 'top', horizontalPosition: 'end' });
        return throwError(error);
      })
    );
  }

  public searchRecipeByFridge(): Observable<RecipeDetails[]> {
    const currentAccount = this.accountService.getCurrentAccount();
    const params = new HttpParams().set('currentAccId', currentAccount?.accountId?.toString() || '');
    return this.http.get<RecipeDetails[]>(this.searchRecipeFridgeUrl, { params }).pipe(
      tap(() => this.snackBar.open('Request succeeded', 'Close', { duration: 3000, verticalPosition: 'top', horizontalPosition: 'end' })),
      catchError(error => {
        this.snackBar.open('Request failed', 'Close', { duration: 3000, verticalPosition: 'top', horizontalPosition: 'end' });
        return throwError(error);
      })
    );
  }

}
