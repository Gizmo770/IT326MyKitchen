import { Component, OnInit } from '@angular/core';
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

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
      //TESTING PURPOSES ONLY!!! TEST
      // this.accountService.currentAccount =
      //   new Account(6, 'TestName', 'TestUsername', 'testpass' +
      //   'test@example.com', '1234567890', '@txt.att.net', 5);
      //TESTING PURPOSES ONLY!!! TEST

    this.updateAccountFields = {
      name: this.accountService.currentAccount ? this.accountService.currentAccount.name : '',
      username: this.accountService.currentAccount ? this.accountService.currentAccount.username : '',
      password: '',
      email: this.accountService.currentAccount ? this.accountService.currentAccount.email : '',
      phoneNumber: this.accountService.currentAccount ? this.accountService.currentAccount.phoneNumber : '',
      phoneCarrier: this.accountService.currentAccount ? this.accountService.currentAccount.phoneCarrier : '',
      lowIngredientThreshold: this.accountService.currentAccount ? this.accountService.currentAccount.lowIngredientThreshold : null,
    };

    this.accountPhoneCarriers = this.accountService.accountPhoneCarriers;
  }

  updateAccount() {
    if (!this.accountService.currentAccount || this.accountService.currentAccount.id === undefined) {
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
