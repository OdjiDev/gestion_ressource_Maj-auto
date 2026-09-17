import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '@core/guards/auth.service';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss'
})
export class Sidebar {

  private readonly authService = inject(AuthService);

  isAdmin(): boolean {
    return this.authService.getRole() === 'ADMIN';
  }

  isGestionnaire(): boolean {
    const role = this.authService.getRole();
    return role === 'ADMIN' || role === 'GESTIONNAIRE';
  }

  isComptable(): boolean {
    const role = this.authService.getRole();
    return role === 'ADMIN' || role === 'COMPTABLE';
  }

  isPersonnel(): boolean {
    const role = this.authService.getRole();
    return role === 'ADMIN' || role === 'PERSONNEL';
  }
}
