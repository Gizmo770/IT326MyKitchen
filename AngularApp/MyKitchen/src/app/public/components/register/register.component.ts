import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CustomValidators } from '../../custom-validator';
import { AuthService } from '../../services/auth-service/auth.service';
import { tap } from 'rxjs';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../interfaces';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    username: new FormControl(null, [Validators.required]),
    name: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
    passwordConfirm: new FormControl(null, [Validators.required])
  },
    // add custom Validators to the form, to make sure that password and passwordConfirm are equal
    { validators: CustomValidators.passwordsMatching }
  )

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  register() {
    if (!this.registerForm.valid) {
      return;
    }
    // this.authService.register(this.registerForm.value as unknown as RegisterRequest).pipe(
    //   // If registration was successfull, then navigate to login route
    //   tap(() => this.router.navigate(['../login']))
    // ).subscribe();
    this.authService.createAccount(this.registerForm.value.name as unknown as string, this.registerForm.value.email as unknown as string, this.registerForm.value.password as unknown as string).pipe(
      tap(() => this.router.navigate(['../login']))
    ).subscribe();
  }

}