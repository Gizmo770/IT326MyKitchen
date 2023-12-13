import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Account } from 'src/app/models/account';

import { AccountService } from 'src/app/services/account.service';

import { EmailService } from 'src/app/services/email.service';

@Component({
  selector: 'app-share-list',
  templateUrl: './share-list.component.html',
  styleUrls: ['./share-list.component.scss']
})
export class ShareListComponent implements OnInit {

  displayEmailForm: boolean = false;
  displayTextForm: boolean = false;

  emailForm = new FormGroup({
    emailToSendTo: new FormControl(''),
  });

  textForm = new FormGroup({
    numberToSendTo: new FormControl(''),
    recipientPhoneCarrier: new FormControl(''),
  });

  phoneCarriers: any;

  constructor(
    private emailService: EmailService,
    private accountService: AccountService,
    private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    // //TESTING PURPOSES ONLY!!! TEST
    // this.accountService.currentAccount =
    // new Account(12, 'TestName', 'TestUsername', 'testpass' +
    // 'test@example.com', '1234567890', '@txt.att.net', 5);
    // //TESTING PURPOSES ONLY!!! TEST
    this.phoneCarriers = this.accountService.accountPhoneCarriers;
  }

  emailShoppingList() {
    const accountId = this.accountService.getCurrentAccount()?.accountId;
    if (this.emailForm && accountId !== undefined) {
      const emailToSendTo = this.emailForm.get('emailToSendTo')?.value;
      if (emailToSendTo) {
        this.emailService.sendShoppingListThroughEmail(accountId, emailToSendTo).subscribe();
      }
    } else {
      this.snackBar.open('Account ID is not defined', 'Close', {
        duration: 3000,
      });
    }
  }

  textShoppingList() {
    const accountId = this.accountService.getCurrentAccount()?.accountId;
    if (this.textForm && accountId !== undefined) {
      const phoneNumberToSendTo = this.textForm.get('numberToSendTo')?.value;
      const recipientPhoneCarrier = this.textForm.get('recipientPhoneCarrier')?.value;
      if (phoneNumberToSendTo && recipientPhoneCarrier) {
        this.emailService.sendShoppingListThroughText(accountId, phoneNumberToSendTo, recipientPhoneCarrier).subscribe();
      }
    } else {
      this.snackBar.open('Account ID is not defined', 'Close', {
        duration: 3000,
      });
    }
  }

  public toggleEmailForm() {
    if(this.displayTextForm) {
      this.displayTextForm = false;
    }
    if(!this.displayEmailForm) {
      this.displayEmailForm = true;
    } else {
      this.displayEmailForm = false;
    }
  }

  public toggleTextForm() {
    if(this.displayEmailForm) {
      this.displayEmailForm = false;
    }
    if(!this.displayTextForm) {
      this.displayTextForm = true;
    } else {
      this.displayTextForm = false;
    }
  }


  //TESTING
  public testNotifyExpired() {
    this.emailService.testNotifyExpired().subscribe();
  }

  public testNotifyLow() {
    this.emailService.testNotifyLow().subscribe();
  }
}
