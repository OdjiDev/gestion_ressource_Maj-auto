import { Component, inject, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '@core/auth';
import { LayoutService } from  '@app/layout/main-layout/layout.service';

@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.scss'
})
export class Header {

  private readonly authService = inject(AuthService);
  readonly layout = inject(LayoutService);

  readonly email = computed(() => this.authService.getEmail() || 'Utilisateur');
  readonly role = computed(() => this.authService.getRole() || 'USER');
  readonly initiale = computed(() => this.email().charAt(0).toUpperCase());

  toggleSidebar(): void {
    // Sur mobile : ouvre la sidebar
    // Sur desktop : réduit/agrandit
    if (window.innerWidth < 992) {
      this.layout.toggleMobileSidebar();
    } else {
      this.layout.toggleCollapse();
    }
  }
}
