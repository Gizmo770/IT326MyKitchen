import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CardModule } from 'primeng/card';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { JwtModule, JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';

import { ButtonModule } from 'primeng/button'
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { PasswordModule } from 'primeng/password';
import { MenubarModule } from 'primeng/menubar';
import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FridgeComponent } from './protected/components/fridge/fridge.component';
import { RecipeSearchComponent } from './protected/components/recipe-search/recipe-search.component';
import { ShareListComponent } from './protected/components/share-list/share-list.component';
import { UpdateAccountComponent } from './protected/components/update-account/update-account.component';
import { MenubarComponent } from './protected/components/menubar/menubar.component';
import { ShoplistComponent } from './protected/components/shoplist/shoplist.component';

export const LOCALSTORAGE_TOKEN_KEY = 'myKitchen';

export function tokenGetter() {
  return localStorage.getItem(LOCALSTORAGE_TOKEN_KEY);
}

@NgModule({
  declarations: [
    AppComponent,
    FridgeComponent,
    RecipeSearchComponent,
    ShareListComponent,
    UpdateAccountComponent,
    MenubarComponent,
    ShoplistComponent
  ],
  imports: [
    // PrimeNG Modules
    FormsModule,
    PanelModule,
    ButtonModule,
    CardModule,
    InputTextModule,
    DialogModule,
    DropdownModule,
    PasswordModule,
    MenubarModule,
    TableModule,
    // Angular Modules
    BrowserAnimationsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    HttpClientModule,
    CalendarModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['localhost:4200', 'localhost:8081']
      }
    })
  ],
  providers: [JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule { }
