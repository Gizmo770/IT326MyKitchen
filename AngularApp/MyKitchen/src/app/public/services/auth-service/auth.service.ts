import { LOCALSTORAGE_TOKEN_KEY } from './../../../app.module';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, switchMap, tap, throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from '../../interfaces';
import { Account } from 'src/app/models/account';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private createAccountUrl: string;
  private validateLoginUrl: string;
  private deleteAccountUrl: string;


  constructor(
    private http: HttpClient,
    private snackbar: MatSnackBar,
    private jwtService: JwtHelperService
  ) {
    this.createAccountUrl = 'http://localhost:8081/account/create';
    this.validateLoginUrl = 'http://localhost:8081/account/login';
    this.deleteAccountUrl = 'http://localhost:8081/account/delete';
  }

  public getCurrentUsername(): string | null {
    const username = localStorage.getItem(LOCALSTORAGE_TOKEN_KEY);
    return username ? JSON.parse(username) : null;
  }

  public login(username: string, password: string): Observable<Account> {
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post<Account>(this.validateLoginUrl, null, { params }).pipe(
      tap((res: Account) => {
        if (res && res.userName) {
          localStorage.setItem(LOCALSTORAGE_TOKEN_KEY, JSON.stringify(res.userName));
          this.snackbar.open('Login Successful', 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          });
        } else {
          throw new Error('No account or username in response');
        }
      }),
      catchError(error => {
        console.log('Error logging in:', error);
        this.snackbar.open('Login Unsuccessful', 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        });
        return throwError(() => error);
      })
    );
  }

  public createAccount(name: string, username: string, password: string): Observable<boolean> {
    const params = new HttpParams()
      .set('name', name)
      .set('username', username)
      .set('password', password);

    return this.http.post<any>(this.createAccountUrl, null, { params }).pipe(
      map(response => response ? true : false),
      catchError(error => {
        console.error('Error creating account:', error);
        return of(false); // Return a default value in case of an error
      })
    );
  }

  public deleteAccount(id: number): Observable<boolean> {
    const params = new HttpParams()
      .set('accountId', id);
    return this.http.delete<any>(this.deleteAccountUrl, { params }).pipe(
      map(response => response ? true : false),
      catchError(error => {
        console.error('Error deleting account:', error);
        return of(false); // Return a default value in case of an error
      })
    );
  }


  /*
   Get the user fromt the token payload
   */
  getLoggedInUser() {
    const decodedToken = this.jwtService.decodeToken();
    return decodedToken.user;
  }
}
