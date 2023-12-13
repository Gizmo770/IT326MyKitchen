import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Account } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.scss']
})
export class UpdateAccountComponent implements OnInit {

  updateAccountFields: any;
  accountPhoneCarriers: any;

  constructor(private accountService: AccountService,
    private snackBar: MatSnackBar) {}

  ngOnInit(): void {
      // //TESTING PURPOSES ONLY!!! TEST
      // this.accountService.getCurrentAccount() =
      //   new Account(12, 'Gian Garnica', 'gjgarn1test', 'testpass' +
      //   'gjgarn1@ilstu.edu', '8157355994', 'AT&T', 1);
      // //TESTING PURPOSES ONLY!!! TEST

    this.updateAccountFields = {
      name: this.accountService.getCurrentAccount()?.name,
      username: this.accountService.getCurrentAccount()?.userName,
      password: '',
      email: this.accountService.getCurrentAccount()?.email,
      phoneNumber: this.accountService.getCurrentAccount()?.phoneNumber,
      phoneCarrier: this.accountService.getCurrentAccount()?.phoneCarrier,
      lowIngredientThreshold: this.accountService.getCurrentAccount()?.lowIngredientThreshold
    };

    this.accountPhoneCarriers = this.accountService.accountPhoneCarriers;
  }

  updateAccount() {
    if (!this.accountService.getCurrentAccount() === undefined) {
      throw new Error('Current account or account ID is not defined');
    }

    if (this.updateAccountFields.lowIngredientThreshold === null) {
      throw new Error('Low Ingredient Threshold is not defined');
    }

    if (!this.updateAccountFields.name || !this.updateAccountFields.username ||
      !this.updateAccountFields.password || !this.updateAccountFields.email || !this.updateAccountFields.phoneNumber) {
      this.snackBar.open('One or more account fields are not defined', 'Close', {
        duration: 3000,
      });
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
      this.accountService.setCurrentAccount(updatedAccount);
      this.snackBar.open('Account updated successfully', 'Close', {
        duration: 3000,
      });
      setTimeout(() => {
        location.reload();
      }, 3000);
    });
  }
}
