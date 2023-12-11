import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private sendEmailUrl: string;
  private sendTextUrl: string;

  constructor(private http: HttpClient) {
    this.sendEmailUrl = 'http://localhost:8081/share-list/email';
    this.sendTextUrl = 'http://localhost:8081/share-list/text';
  }

  public sendShoppingListThroughEmail(
    currentAccId: number, emailToSendTo: string): Observable<any> {
    const params = new HttpParams()
      .set('currentAccId', currentAccId)
      .set('emailToSendTo', emailToSendTo);
      return this.http.post<any>(this.sendEmailUrl, params);
    }

  public sendShoppingListThroughText(
    currentAccId: number, phoneNumberToSendTo: string, recipientPhoneCarrier: string): Observable<any> {
    const params = new HttpParams()
      .set('currentAccId', currentAccId)
      .set('phoneNumberToSendTo', phoneNumberToSendTo)
      .set('recipientPhoneCarrier', recipientPhoneCarrier);
      return this.http.post<any>(this.sendTextUrl, params);
  }
}
