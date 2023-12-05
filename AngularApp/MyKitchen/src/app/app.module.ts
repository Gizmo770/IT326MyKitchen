import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CardModule } from 'primeng/card';
import { HttpClientModule } from '@angular/common/http';
import { ButtonModule } from 'primeng/button'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FridgeComponent } from './fridge/fridge.component';
import { RecipeSearchComponent } from './recipe-search/recipe-search.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    FridgeComponent,
    RecipeSearchComponent
  ],
  imports: [
    // PrimeNG Modules
    ButtonModule,
    CardModule,
    // Angular Modules
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
