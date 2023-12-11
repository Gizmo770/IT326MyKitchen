import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm: FormGroup = new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
  });

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

}