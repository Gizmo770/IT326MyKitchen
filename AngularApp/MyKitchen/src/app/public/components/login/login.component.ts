import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service/auth.service';
import { Account } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  private currentAccount?: Account;

  loginForm: FormGroup = new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
  });

  deleteForm: FormGroup = new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
  })

  constructor(
    private router: Router,
    private authService: AuthService,
  ) { }

  login() {
    if (!this.loginForm.valid) {
      return;
    }
    const usernameValue = this.loginForm.get('username')?.value ?? '';
    const passwordValue = this.loginForm.get('password')?.value ?? '';

    this.authService.login(usernameValue, passwordValue).pipe(
      tap(() => this.router.navigate(['../../protected/dashboard']))
    ).subscribe();
  }

  delete() {
    if (!this.deleteForm.valid) {
      return;
    }
    const idValue = this.currentAccount?.accountId ?? 0;

    this.authService.deleteAccount(idValue).pipe(
      tap(() => this.router.navigate(['../register']))
    ).subscribe();
  }

}
