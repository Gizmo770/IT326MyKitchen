import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { LOCALSTORAGE_TOKEN_KEY } from 'src/app/app.module';
import { Menu } from 'primeng/menu';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  items: MenuItem[] = [];

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.items = [
      {
        label: 'Fridge',
        icon: 'pi pi-server',
        command: () => {
          this.router.navigate(['../../components/fridge']);
        }
      },
      {
        label: 'Recipe',
        icon: 'pi pi-book',
        command: () => {
          this.router.navigate(['../../components/recip-search']);
        }
      },
      {
        label: 'Share Shopping List!',
        icon: 'pi pi-share-alt',
        command: () => {
          this.router.navigate(['../../share-shopping-list']);
        }
      },
      {
        label: 'Update Account',
        icon: 'pi pi-user-edit',
        command: () => {
          this.router.navigate(['../../update-account']);
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
    this.router.navigate(['../../']);
  }

}
