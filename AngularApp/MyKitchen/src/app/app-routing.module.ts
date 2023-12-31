import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth-guard/auth.guard';
import { FridgeComponent } from './protected/components/fridge/fridge.component';
import { RecipeSearchComponent } from './protected/components/recipe-search/recipe-search.component';
import { ShareListComponent } from './protected/components/share-list/share-list.component';
import { UpdateAccountComponent } from './protected/components/update-account/update-account.component';

const routes: Routes = [
  {
    // Lazy Loading the public module (all children routes will be under '/public/{route from lazy loaded module}')
    path: 'public',
    loadChildren: () => import('./public/public.module').then(m => m.PublicModule)
  },
  {
    // Lazy Loading the protected module (all children routes will be under '/protected/{route from lazy loaded module}')
    // The guard will check if the user is having a jwt, otherwise he will be redirected to the base route
    path: 'protected',
    canActivate: [AuthGuard],
    loadChildren: () => import('./protected/protected.module').then(m => m.ProtectedModule)
  },
  {
    path: 'fridge',
    component: FridgeComponent
  },
  {
    path: 'recipe',
    component: RecipeSearchComponent
  },
  {
    path: 'share-shopping-list',
    component: ShareListComponent
  },
  {
    path: 'update-account',
    component: UpdateAccountComponent
  },
  {
    // Redirects all paths that are not matching to the 'public' route/path
    path: '**',
    redirectTo: 'public',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
