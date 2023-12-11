import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CardModule } from 'primeng/card';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from '@angular/forms';
import { ShoppingListComponent } from './components/shopping-list/shopping-list.component';
import { ButtonModule } from 'primeng/button'
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FridgeComponent } from './fridge/fridge.component';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';

export const LOCALSTORAGE_TOKEN_KEY = 'angular_material_login_and_register_example';

export function tokenGetter() {
  return localStorage.getItem(LOCALSTORAGE_TOKEN_KEY);
}

@NgModule({
  declarations: [
    AppComponent,
    FridgeComponent,
    RecipeSearchComponent
  ],
  imports: [
    // PrimeNG Modules
    FormsModule,
    PanelModule,
    ButtonModule,
    CardModule,
    InputTextModule,
    // Angular Modules
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
