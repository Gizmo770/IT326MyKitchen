import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FridgeComponent } from './fridge/fridge.component';
import { RecipeSearchComponent } from './recipe-search/recipe-search.component';

const routes: Routes = [
  { path: '', redirectTo: '/fridge', pathMatch: 'full' },
  { path: 'fridge', component: FridgeComponent },
  { path: 'recipe-search', component: RecipeSearchComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
