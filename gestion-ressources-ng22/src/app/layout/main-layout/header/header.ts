import { Component, inject, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '@core/guards/auth.service';

@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.scss'
})
export class Header {

  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  email = computed(() => this.authService.getEmail() || 'Utilisateur');
  role = computed(() => this.authService.getRole() || 'USER');

  logout(): void {
    this.authService.logout();
  }
}
