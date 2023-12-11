import { LOCALSTORAGE_TOKEN_KEY } from './../../../app.module';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, switchMap, tap } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Account } from '../../../models/account';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from '../../interfaces';

export const fakeLoginResponse: LoginResponse = {
  // fakeAccessToken.....should all come from real backend
  accessToken: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c',
  refreshToken: {
    id: 1,
    userId: 2,
    token: 'fakeRefreshToken...should al come from real backend',
    refreshCount: 2,
    expiryDate: new Date(),
  },
  tokenType: 'JWT'
}

export const fakeRegisterResponse: RegisterResponse = {
  status: 200,
  message: 'Registration sucessfull.'
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentAccount?: Account;

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

  /*
   Due to the '/api' the url will be rewritten by the proxy, e.g. to http://localhost:8080/api/auth/login
   this is specified in the src/proxy.conf.json
   the proxy.conf.json listens for /api and changes the target. You can also change this in the proxy.conf.json

   The `..of()..` can be removed if you have a real backend, at the moment, this is just a faked response
  */
  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    // return of(fakeLoginResponse).pipe(
    //   tap((res: LoginResponse) => localStorage.setItem(LOCALSTORAGE_TOKEN_KEY, res.accessToken)),
    //   tap(() => this.snackbar.open('Login Successfull', 'Close', {
    //     duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
    //   }))
    // );
    return this.http.post<LoginResponse>('http://localhost:8081/account/login', loginRequest).pipe(
      tap((res: LoginResponse) => localStorage.setItem(LOCALSTORAGE_TOKEN_KEY, res.accessToken)),
      tap(() => this.snackbar.open('Login Successfull', 'Close', {
        duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
      }))
    );
  }

  /*
   The `..of()..` can be removed if you have a real backend, at the moment, this is just a faked response
  */
  register(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    //   // TODO
    // return of(fakeRegisterResponse).pipe(
    //   tap((res: RegisterResponse) => this.snackbar.open(`User created successfully`, 'Close', {
    //     duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
    //   })),
    // );
    return this.http.post<RegisterResponse>('http://localhost:8081/account/create', registerRequest).pipe(
      tap((res: RegisterResponse) => this.snackbar.open(`User created successfully`, 'Close', {
        duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
      }))
    )
  }

  public createAccount(name: string, username: string, password: string): Observable<boolean> {
    const body = {
      name: name,
      username: username,
      password: password
    }; // Use an object for the request body

    return this.http.post<any>(this.createAccountUrl, { body }).pipe(
      map(response => response ? true : false),
      catchError(error => {
        console.error('Error creating account:', error);
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