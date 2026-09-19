import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { forkJoin } from 'rxjs';
import { AuthService } from '@core/auth';
import { DashboardService } from '../dashboard.service';
import { DashboardStats, AlertProduit, RecentFacture } from '../dashboard.model';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard implements OnInit {

  private readonly authService = inject(AuthService);
  private readonly dashboardService = inject(DashboardService);

  // ==================== STATE ====================
  readonly stats = signal<DashboardStats | null>(null);
  readonly ruptures = signal<AlertProduit[]>([]);
  readonly factures = signal<RecentFacture[]>([]);
  readonly loading = signal(true);
  readonly error = signal<string | null>(null);

  // ==================== COMPUTED ====================
  readonly email = computed(() => this.authService.getEmail() || 'Utilisateur');
  readonly role = computed(() => this.authService.getRole() || 'USER');
  readonly dateAujourdhui = computed(() => {
    const now = new Date();
    return now.toLocaleDateString('fr-FR', {
      weekday: 'long',
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    });
  });

  // ==================== LIFECYCLE ====================

  ngOnInit(): void {
    this.loadDashboard();
  }

  // ==================== CHARGEMENT ====================

  loadDashboard(): void {
    this.loading.set(true);
    this.error.set(null);

    forkJoin({
      stats: this.dashboardService.getStatsSimple(),
      ruptures: this.dashboardService.getProduitsEnRupture(),
      factures: this.dashboardService.getDernieresFactures()
    }).subscribe({
      next: ({ stats, ruptures, factures }) => {
        this.stats.set(stats);
        this.ruptures.set(ruptures);
        this.factures.set(factures);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set('Erreur lors du chargement du tableau de bord');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  // ==================== HELPERS ====================

  getStatutBadge(quantite: number): string {
    if (quantite <= 0) return 'bg-danger';
    if (quantite < 10) return 'bg-warning';
    return 'bg-success';
  }

  getStatutLabel(quantite: number): string {
    if (quantite <= 0) return 'Rupture';
    if (quantite < 10) return 'Faible';
    return 'OK';
  }
}
