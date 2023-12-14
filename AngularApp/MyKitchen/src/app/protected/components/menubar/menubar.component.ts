import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Menu } from 'primeng/menu';
import { LOCALSTORAGE_TOKEN_KEY } from 'src/app/app.module';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrl: './menubar.component.scss'
})
export class MenubarComponent {

  items: MenuItem[] = [];

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.items = [
      {
        label: 'Dashboard',
        icon: 'pi pi-home',
        command: () => {
          this.router.navigate(['/protected/dashboard']); // Corrected navigation path
        }
      },
      {
        label: 'Fridge',
        icon: 'pi pi-server',
        command: () => {
          this.router.navigate(['/protected/fridge']); // Corrected navigation path
        }
      },
      {
        label: 'Shopping List',
        icon: 'pi pi-shopping-cart',
        command: () => {
          this.router.navigate(['/protected/shopping-list']); // Corrected navigation path
        }
      },
      {
        label: 'Share Shopping List!',
        icon: 'pi pi-share-alt',
        command: () => {
          this.router.navigate(['/protected/share-shopping-list']); // Corrected navigation path
        }
      },
      {
        label: 'Recipe',
        icon: 'pi pi-book',
        command: () => {
          this.router.navigate(['/protected/recipe-search']); // Corrected navigation path
        }
      },
      {
        label: 'Update Account',
        icon: 'pi pi-user-edit',
        command: () => {
          this.router.navigate(['/protected/update-account']); // Corrected navigation path
        }
      },
      {
        label: 'Logout',
        icon: 'pi pi-sign-out',
        command: () => this.logout()
      }
    ];
  }

  logout() {
    // Removes the jwt token from the local storage, so the user gets logged out & then navigate back to the "public" routes
    localStorage.removeItem(LOCALSTORAGE_TOKEN_KEY);
    this.router.navigate(['../../']); // Corrected navigation path
  }
}
