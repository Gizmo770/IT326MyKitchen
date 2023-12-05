import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, map, tap } from 'rxjs';

import { Account } from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private currentAccount?: Account;

  private createAccountUrl: string;
  private validateLoginUrl: string;
  private deleteAccountUrl: string;

  constructor(private http: HttpClient) {
    this.createAccountUrl = 'http://localhost:8081/account/create';
    this.validateLoginUrl = 'http://localhost:8081/account/login';
    this.deleteAccountUrl = 'http://localhost:8081/account/delete';
  }

  //In HTML, if it returns true, then the account was created successfully
  //We should then return to the login page
  public createAccount(name: string, username: string, password: string): Observable<boolean> {
    const params = new HttpParams()
      .set('name', name)
      .set('username', username)
      .set('password', password);
    return this.http.post(this.createAccountUrl, params).pipe(
      map(response => response ? true : false)
    );
  }

  //In HTML, use *ngIf="accountService.currentAccount" to check if the
  // user is logged in. If they are, then go to the main page.
  public logIn(username: string, password: string): Observable<Account> {
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.http.post<Account>(this.validateLoginUrl, params)
      .pipe(tap(account => this.currentAccount = account));
  }

  //If returns true, then the account was deleted successfully
  //We should then return to the login page
  public deleteCurrentAccount(): Observable<boolean> {
    if (!this.currentAccount) {
      throw new Error('No current account to delete');
    }

    const params = new HttpParams().set('accountId', this.currentAccount.id.toString());
    return this.http.delete(this.deleteAccountUrl, { params }).pipe(
      map(response => response ? true : false)
    );
  }
}
