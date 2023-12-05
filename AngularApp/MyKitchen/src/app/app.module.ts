import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from '@angular/forms';

import { ButtonModule } from 'primeng/button'
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeSearchComponent
  ],
  imports: [
    // PrimeNG Modules
    FormsModule,
    PanelModule,
    ButtonModule,
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
