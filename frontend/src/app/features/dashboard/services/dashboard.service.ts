import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '@env/environment';
import { DashboardStats, AlertProduit, RecentFacture } from '../models/dashboard.model';

@Injectable({ providedIn: 'root' })
export class DashboardService {

  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.baseURL || environment.baseURL;

  /**
   * Charge toutes les stats du dashboard en parallèle.
   * Si une requête échoue, on retourne une valeur par défaut.
   */
  getStats(): Observable<DashboardStats> {
    return forkJoin({
      produits: this.http.get<any>(`${this.baseUrl}/produits?size=1`)
        .pipe(catchError(() => of({ totalElements: 0 }))),
      categories: this.http.get<any>(`${this.baseUrl}/categories?size=1`)
        .pipe(catchError(() => of({ totalElements: 0 }))),
      fournisseurs: this.http.get<any>(`${this.baseUrl}/fournisseurs?size=1`)
        .pipe(catchError(() => of({ totalElements: 0 }))),
      factures: this.http.get<any>(`${this.baseUrl}/factures?size=1`)
        .pipe(catchError(() => of({ totalElements: 0 }))),
      personels: this.http.get<any>(`${this.baseUrl}/personels?size=1`)
        .pipe(catchError(() => of({ totalElements: 0 }))),
      ruptures: this.http.get<any[]>(`${this.baseUrl}/produits/alertes/rupture`)
        .pipe(catchError(() => of([])))
    }).pipe(
      catchError(() => of(this.emptyStats()))
    ).pipe(
      // Transforme en DashboardStats
      catchError(() => of(this.emptyStats()))
    ) as any;
  }

  /**
   * Version plus simple : récupère les stats via un endpoint dédié.
   * Si le backend expose /api/dashboard/stats, utilise ça.
   */
  getStatsSimple(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${this.baseUrl}/dashboard/stats`)
      .pipe(catchError(() => of(this.emptyStats())));
  }

  getProduitsEnRupture(): Observable<AlertProduit[]> {
    return this.http.get<AlertProduit[]>(`${this.baseUrl}/produits/alertes/rupture`)
      .pipe(catchError(() => of([])));
  }

  getDernieresFactures(): Observable<RecentFacture[]> {
    return this.http.get<RecentFacture[]>(`${this.baseUrl}/factures?size=5&sort=datecommande,desc`)
      .pipe(catchError(() => of([])));
  }

  private emptyStats(): DashboardStats {
    return {
      totalProduits: 0,
      totalCategories: 0,
      totalFournisseurs: 0,
      totalFactures: 0,
      produitsEnRupture: 0,
      produitsSousSeuil: 0,
      valeurStock: 0,
      totalPersonels: 0
    };
  }
}
