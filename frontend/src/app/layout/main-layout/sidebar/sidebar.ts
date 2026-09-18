import { Component, inject, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '@core/guards/auth.service';
import { LayoutService } from   '@app/layout/main-layout/layout.service';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss'
})
export class Sidebar {

  readonly authService = inject(AuthService);
  readonly layout = inject(LayoutService);

  readonly email = computed(() => this.authService.getEmail() || 'Utilisateur');
  readonly role = computed(() => this.authService.getRole() || 'USER');
  readonly initiale = computed(() => this.email().charAt(0).toUpperCase());

  readonly isAdmin = computed(() => this.authService.getRole() === 'ADMIN');
  readonly isGestionnaire = computed(() => {
    const r = this.authService.getRole();
    return r === 'ADMIN' || r === 'GESTIONNAIRE';
  });
  readonly isComptable = computed(() => {
    const r = this.authService.getRole();
    return r === 'ADMIN' || r === 'COMPTABLE';
  });
  readonly isPersonnel = computed(() => {
    const r = this.authService.getRole();
    return r === 'ADMIN' || r === 'PERSONNEL';
  });


  //En attendant la VENTE a effacer apres la phase demo
  readonly isUser = computed(() => {
    const r = this.authService.getRole();
    return r === ' ADMIN' || r === 'USER';
  });

  onCloseMobile(): void {
    this.layout.closeMobileSidebar();
  }

  logout(): void {
    this.authService.logout();
  }
}
