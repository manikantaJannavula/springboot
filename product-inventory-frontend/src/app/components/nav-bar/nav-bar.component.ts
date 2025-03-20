import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
  standalone:false
})
export class NavBarComponent {
  constructor(public authService: AuthService, private router: Router) { }

  logout(): void {
    this.authService.setLoggedIn(false);
    this.router.navigate(['/welcome']);
  }
}