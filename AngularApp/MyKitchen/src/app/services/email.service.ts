import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';


@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private sendEmailUrl: string;
  private sendTextUrl: string;
  private testNotifyExpiredUrl: string;
  private testNotifyLowUrl: string;

  constructor(
    private http: HttpClient,
    private snackBar: MatSnackBar) {
    this.sendEmailUrl = 'http://localhost:8081/share-list/email';
    this.sendTextUrl = 'http://localhost:8081/share-list/text';
    this.testNotifyExpiredUrl = 'http://localhost:8081/notify/expired';
    this.testNotifyLowUrl = 'http://localhost:8081/notify/low';
  }

  public sendShoppingListThroughEmail(
    currentAccId: number, emailToSendTo: string): Observable<any> {
    const params = new HttpParams()
      .set('currentAccId', currentAccId.toString())
      .set('emailToSendTo', emailToSendTo);
    return this.http.post(this.sendEmailUrl, params, { responseType: 'text' }).pipe(
      tap(() => {
        this.snackBar.open('Email sent', 'Close', { duration: 2000 });
        setTimeout(() => {
          location.reload();
        }, 2000);
      }),
      catchError(error => {
        this.snackBar.open('SERVER ERROR: Failed to send email', 'Close', { duration: 2000 });
        return throwError(error);
      })
    );
  }

  public sendShoppingListThroughText(
    currentAccId: number, phoneNumberToSendTo: string, recipientPhoneCarrier: string): Observable<any> {
    const params = new HttpParams()
      .set('currentAccId', currentAccId.toString())
      .set('phoneNumberToSendTo', phoneNumberToSendTo)
      .set('recipientPhoneCarrier', recipientPhoneCarrier);
    return this.http.post(this.sendTextUrl, params, { responseType: 'text' }).pipe(
      tap(() => {
        this.snackBar.open('Text sent', 'Close', { duration: 2000 });
        setTimeout(() => {
          location.reload();
        }, 2000);
      }),
      catchError(error => {
        this.snackBar.open('SERVER ERROR: Failed to send text', 'Close', { duration: 2000 });
        return throwError(error);
      })
    );
  }


  public testNotifyExpired(): Observable<any> {
    return this.http.post(this.testNotifyExpiredUrl, {}, { responseType: 'text' }).pipe(
      tap(() => {
        this.snackBar.open('Test notification sent', 'Close', { duration: 2000 });
        setTimeout(() => {
          location.reload();
        }, 2000);
      }),
      catchError(error => {
        this.snackBar.open('SERVER ERROR: Failed to send test notification', 'Close', { duration: 2000 });
        return throwError(error);
      })
    );
  }

  public testNotifyLow(): Observable<any> {
    return this.http.post(this.testNotifyLowUrl, {}, { responseType: 'text' }).pipe(
      tap(() => {
        this.snackBar.open('Test notification sent', 'Close', { duration: 2000 });
        setTimeout(() => {
          location.reload();
        }, 2000);
      }),
      catchError(error => {
        this.snackBar.open('SERVER ERROR: Failed to send test notification', 'Close', { duration: 2000 });
        return throwError(error);
      })
    );
  }

}
