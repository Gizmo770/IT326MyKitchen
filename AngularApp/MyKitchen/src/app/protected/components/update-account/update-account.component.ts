import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Account } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.scss']
})
export class UpdateAccountComponent implements OnInit {

  updateAccountForm: FormGroup;
  updateAccountFields: any;
  accountPhoneCarriers: any;

  constructor(private accountService: AccountService) {
    this.updateAccountForm = new FormGroup({});
  }

  ngOnInit(): void {
    this.updateAccountForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phoneNumber': new FormControl(null, Validators.required),
      'phoneCarrier': new FormControl(null, Validators.required),
      'lowIngredientThreshold': new FormControl(null, Validators.required)
    });

      //TESTING PURPOSES ONLY!!! TEST
      this.accountService.currentAccount =
        new Account(12, 'Gian Garnica', 'gjgarn1test', 'testpass' +
        'gjgarn1@ilstu.edu', '8157355994', 'AT&T', 1);
      //TESTING PURPOSES ONLY!!! TEST

    this.updateAccountFields = {
      name: this.accountService.currentAccount ? this.accountService.currentAccount.name : '',
      username: this.accountService.currentAccount ? this.accountService.currentAccount.userName : '',
      password: '',
      email: this.accountService.currentAccount ? this.accountService.currentAccount.email : '',
      phoneNumber: this.accountService.currentAccount ? this.accountService.currentAccount.phoneNumber : '',
      phoneCarrier: this.accountService.currentAccount ? this.accountService.currentAccount.phoneCarrier : '',
      lowIngredientThreshold: this.accountService.currentAccount ? this.accountService.currentAccount.lowIngredientThreshold : null,
    };

    this.accountPhoneCarriers = this.accountService.accountPhoneCarriers;
  }

  updateAccount() {
    if (!this.accountService.currentAccount || this.accountService.currentAccount.accountId === undefined) {
      throw new Error('Current account or account ID is not defined');
    }

    if (this.updateAccountFields.lowIngredientThreshold === null) {
      throw new Error('Low Ingredient Threshold is not defined');
    }

    if (!this.updateAccountFields.name || !this.updateAccountFields.username ||
      !this.updateAccountFields.password || !this.updateAccountFields.email || !this.updateAccountFields.phoneNumber) {
      throw new Error('One or more account fields are not defined');
    }

    if (!this.updateAccountFields.phoneCarrier) {
      throw new Error('Phone Carrier is not defined');
    }

    if(this.updateAccountFields.lowIngredientThreshold == undefined) {
      throw new Error('Low Ingredient Threshold is not defined');
    }

    this.accountService.updateAccount(
      this.updateAccountFields.name,
      this.updateAccountFields.username,
      this.updateAccountFields.password,
      this.updateAccountFields.email,
      this.updateAccountFields.phoneNumber,
      this.updateAccountFields.phoneCarrier,
      this.updateAccountFields.lowIngredientThreshold
    ).subscribe(updatedAccount => {
      console.log('Account updated:', updatedAccount);
      this.accountService.currentAccount = updatedAccount;
      alert('Account updated successfully');
      setTimeout(() => {
        location.reload();
      }, 3000);
    });
  }
}
