import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, map, tap } from 'rxjs';

import { Account } from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService implements OnInit {

  public currentAccount?: Account;

  private createAccountUrl: string;
  private validateLoginUrl: string;
  private deleteAccountUrl: string;
  private updateAccountUrl: string;

  constructor(private http: HttpClient) {
    this.createAccountUrl = 'http://localhost:8081/account/create';
    this.validateLoginUrl = 'http://localhost:8081/account/login';
    this.deleteAccountUrl = 'http://localhost:8081/account/delete';
    this.updateAccountUrl = 'http://localhost:8081/account/update';
  }

  ngOnInit(): void {
    // //TESTING PURPOSES ONLY!!! TEST
    // this.currentAccount =
    // new Account(6, 'TestName', 'TestUsername', 'testpass' +
    // 'test@example.com', '1234567890', '@txt.att.net', 5);
    // //TESTING PURPOSES ONLY!!! TEST
  }

  accountPhoneCarriers = [
    {label: 'AT&T', value: 'AT&T'},
    {label: 'Verizon', value: 'Verizon'},
    {label: 'T-Mobile', value: 'T-Mobile'},
    {label: 'Sprint', value: 'Sprint'},
    {label: 'Virgin Mobile', value: 'Virgin Mobile'},
    {label: 'Tracfone', value: 'Tracfone'},
    {label: 'Metro PCS', value: 'Metro PCS'},
    {label: 'Boost Mobile', value: 'Boost Mobile'},
    {label: 'Cricket', value: 'Cricket'},
    {label: 'Republic Wireless', value: 'Republic Wireless'},
    {label: 'Google Fi', value: 'Google Fi'},
    {label: 'U.S. Cellular', value: 'U.S. Cellular'},
    {label: 'Ting', value: 'Ting'},
    {label: 'Consumer Cellular', value: 'Consumer Cellular'},
    {label: 'C-Spire', value: 'C-Spire'},
    {label: 'Page Plus', value: 'Page Plus'},
    {label: 'Xfinity Mobile', value: 'Xfinity Mobile'}
  ];

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
    if (!this.currentAccount || this.currentAccount.accountId === undefined) {
      throw new Error('Current account or account ID is not defined');
    }

    const params = new HttpParams().set('accountId', this.currentAccount.accountId.toString());    return this.http.delete(this.deleteAccountUrl, { params }).pipe(
      map(response => response ? true : false)
    );
  }

  public updateAccount(name: string,
    username: string, password: string, email: string,
    phoneNumber: string, phoneCarrier: string,
    lowIngredientThreshold: number): Observable<Account> {

    if (!this.currentAccount || this.currentAccount.accountId === undefined) {
      throw new Error('Current account or account ID is not defined');
    }

    const params = new HttpParams()
      .set('accountId', this.currentAccount?.accountId.toString())
      .set('name', name)
      .set('username', username)
      .set('password', password)
      .set('email', email)
      .set('phoneNumber', phoneNumber)
      .set('phoneCarrier', phoneCarrier)
      .set('lowIngredientThreshold', lowIngredientThreshold);
    return this.http.post<Account>(this.updateAccountUrl, params)
      .pipe(tap(account => this.currentAccount = account));
    }
  }
