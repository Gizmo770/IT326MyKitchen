import { Component } from '@angular/core';

import { FormGroup, FormControl } from '@angular/forms';

import { EmailService } from 'src/app/services/email.service';

@Component({
  selector: 'app-share-list',
  templateUrl: './share-list.component.html',
  styleUrls: ['./share-list.component.scss']
})
export class ShareListComponent {
  displayEmailModal: boolean = false;
  displayTextModal: boolean = false;

  emailForm = new FormGroup({
    emailToSendTo: new FormControl(''),
  });

  textForm = new FormGroup({
    numberToSendTo: new FormControl(''),
    recipientPhoneCarrier: new FormControl(''),
  });

  phoneCarriers = [
    {label: 'AT&T', value: '@txt.att.net'},
    {label: 'Verizon', value: '@vtext.com'},
    {label: 'T-Mobile', value: '@tmomail.net'},
    {label: 'Sprint', value: '@messaging.sprintpcs.com'},
    {label: 'Virgin Mobile', value: '@vmobl.com'},
    {label: 'Tracfone', value: '@mmst5.tracfone.com'},
    {label: 'Metro PCS', value: '@mymetropcs.com'},
    {label: 'Boost Mobile', value: '@sms.myboostmobile.com'},
    {label: 'Cricket', value: '@sms.cricketwireless.net'},
    {label: 'Republic Wireless', value: '@text.republicwireless.com'},
    {label: 'Google Fi', value: '@msg.fi.google.com'},
    {label: 'U.S. Cellular', value: '@email.us'},
    {label: 'Ting', value: '@message.ting.com'},
    {label: 'Consumer Cellular', value: '@mailmymobile.net'},
    {label: 'C-Spire', value: '@cspire1.com'},
    {label: 'Page Plus', value: '@vtext.com'},
    {label: 'Xfinity Mobile', value: '@vtext.com'}
  ];

  constructor(private emailService: EmailService) {}

  emailShoppingList() {
    if (this.emailForm) {
      const emailToSendTo = this.emailForm.get('emailToSendTo')?.value;
      if (emailToSendTo) {
        this.emailService.sendShoppingListThroughEmail(1, emailToSendTo).subscribe();
        this.displayEmailModal = false;
      }
    }
  }

  textShoppingList() {
    if (this.textForm) {
      const phoneNumberToSendTo = this.textForm.get('numberToSendTo')?.value;
      const recipientPhoneCarrier = this.textForm.get('recipientPhoneCarrier')?.value;
      if (phoneNumberToSendTo && recipientPhoneCarrier) {
        this.emailService.sendShoppingListThroughText(1, phoneNumberToSendTo, recipientPhoneCarrier).subscribe();
        this.displayTextModal = false;
      }
    }
  }
}
