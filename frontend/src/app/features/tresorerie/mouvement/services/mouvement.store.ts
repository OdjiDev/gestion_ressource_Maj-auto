import { Injectable, inject, signal, computed } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { Mouvement, MouvementCreateRequest } from '../models/mouvement.model';
import { MouvementApiService } from './mouvement-api.service';

@Injectable({ providedIn: 'root' })
export class MouvementStore {

  private readonly api = inject(MouvementApiService);

  private readonly _mouvements = signal<Mouvement[]>([]);
  private readonly _loading = signal(false);
  private readonly _error = signal<string | null>(null);

  readonly mouvements = this._mouvements.asReadonly();
  readonly loading = this._loading.asReadonly();
  readonly error = this._error.asReadonly();
  readonly isEmpty = computed(() => this._mouvements().length === 0);
  readonly total = computed(() => this._mouvements().length);

  // Statistiques
  readonly totalEntrees = computed(() =>
    this._mouvements()
      .filter(m => m.type === 'ENTREE' && m.statut === 'VALIDE')
      .reduce((sum, m) => sum + m.montant, 0)
  );

  readonly totalSorties = computed(() =>
    this._mouvements()
      .filter(m => m.type === 'SORTIE' && m.statut === 'VALIDE')
      .reduce((sum, m) => sum + m.montant, 0)
  );

  readonly solde = computed(() => this.totalEntrees() - this.totalSorties());

  loadAll(force = false): void {
    if (!force && this._mouvements().length > 0 && !this._loading()) return;
    this._loading.set(true);
    this._error.set(null);

    this.api.getAllMouvements().subscribe({
      next: (data) => { this._mouvements.set(data); this._loading.set(false); },
      error: (err) => { this._error.set('Erreur chargement'); this._loading.set(false); console.error(err); }
    });
  }

  refresh(): void { this.loadAll(true); }

  create(dto: MouvementCreateRequest): Observable<Mouvement> {
    this._loading.set(true);
    return this.api.addMouvement(dto).pipe(
      tap((created) => { this._mouvements.update(l => [created, ...l]); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur création'); this._loading.set(false); return throwError(() => err); })
    );
  }

  update(id: number, dto: MouvementCreateRequest): Observable<Mouvement> {
    this._loading.set(true);
    return this.api.updateMouvement(id, dto).pipe(
      tap((updated) => { this._mouvements.update(l => l.map(m => m.id === id ? updated : m)); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur modification'); this._loading.set(false); return throwError(() => err); })
    );
  }

  delete(id: number): Observable<void> {
    this._loading.set(true);
    return this.api.deleteMouvement(id).pipe(
      tap(() => { this._mouvements.update(l => l.filter(m => m.id !== id)); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur suppression'); this._loading.set(false); return throwError(() => err); })
    );
  }

  getById(id: number): Mouvement | undefined {
    return this._mouvements().find(m => m.id === id);
  }

  clear(): void { this._mouvements.set([]); this._error.set(null); }
}
