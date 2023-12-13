import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl } from '@angular/forms';
import { Account } from 'src/app/models/account';

import { AccountService } from 'src/app/services/account.service';

import { EmailService } from 'src/app/services/email.service';

@Component({
  selector: 'app-share-list',
  templateUrl: './share-list.component.html',
  styleUrls: ['./share-list.component.scss']
})
export class ShareListComponent implements OnInit {

  displayEmailModal: boolean = false;
  displayTextModal: boolean = false;

  emailForm = new FormGroup({
    emailToSendTo: new FormControl(''),
  });

  textForm = new FormGroup({
    numberToSendTo: new FormControl(''),
    recipientPhoneCarrier: new FormControl(''),
  });

  phoneCarriers: any;

  constructor(private emailService: EmailService, private accountService: AccountService) {}

  ngOnInit(): void {
    // //TESTING PURPOSES ONLY!!! TEST
    // this.accountService.currentAccount =
    // new Account(12, 'TestName', 'TestUsername', 'testpass' +
    // 'test@example.com', '1234567890', '@txt.att.net', 5);
    // //TESTING PURPOSES ONLY!!! TEST
    this.phoneCarriers = this.accountService.accountPhoneCarriers;
  }

  emailShoppingList() {
    if (this.emailForm) {
      const emailToSendTo = this.emailForm.get('emailToSendTo')?.value;
      if (emailToSendTo) {
        this.emailService.sendShoppingListThroughEmail(this.accountService.currentAccount?.accountId ?? 0, emailToSendTo).subscribe();
        this.displayEmailModal = false;
      }
    }
  }

  textShoppingList() {
    if (this.textForm) {
      const phoneNumberToSendTo = this.textForm.get('numberToSendTo')?.value;
      const recipientPhoneCarrier = this.textForm.get('recipientPhoneCarrier')?.value;
      if (phoneNumberToSendTo && recipientPhoneCarrier) {
        this.emailService.sendShoppingListThroughText(this.accountService.currentAccount?.accountId ?? 0, phoneNumberToSendTo, recipientPhoneCarrier).subscribe();
        this.displayTextModal = false;
      }
    }
  }
}
