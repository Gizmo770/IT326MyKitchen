import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FridgeComponent } from './components/fridge/fridge.component';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';
import { ShareListComponent } from './components/share-list/share-list.component';
import { UpdateAccountComponent } from './components/update-account/update-account.component';

// Routes for child Module (protectedModule). Since protected module is lazy loaded in in the
// app-routing.module the full path is `/protected/dashboard`
const routes: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'fridge',
        component: FridgeComponent
    },
    {
        path: 'recipe-search',
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
        path: '**',
        redirectTo: 'login',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProtectedRoutingModule { }
