import { Injectable, inject, signal, computed } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { Compte, CompteCreateRequest } from '../models/compte.model';
import { CompteApiService } from './compte-api.service';

@Injectable({ providedIn: 'root' })
export class CompteStore {
  private readonly api = inject(CompteApiService);
  private readonly _comptes = signal<Compte[]>([]);
  private readonly _loading = signal(false);
  private readonly _error = signal<string | null>(null);

  readonly comptes = this._comptes.asReadonly();
  readonly loading = this._loading.asReadonly();
  readonly error = this._error.asReadonly();
  readonly isEmpty = computed(() => this._comptes().length === 0);
  readonly total = computed(() => this._comptes().length);
  readonly comptesActifs = computed(() => this._comptes().filter(c => c.actif));

  loadAll(force = false): void {
    if (!force && this._comptes().length > 0 && !this._loading()) return;
    this._loading.set(true);
    this._error.set(null);
    this.api.getAllComptes().subscribe({
      next: (d) => { this._comptes.set(d); this._loading.set(false); },
      error: (e) => { this._error.set('Erreur chargement'); this._loading.set(false); console.error(e); }
    });
  }
  refresh(): void { this.loadAll(true); }

  create(dto: CompteCreateRequest): Observable<Compte> {
    this._loading.set(true);
    return this.api.addCompte(dto).pipe(
      tap(c => { this._comptes.update(l => [...l, c]); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur création'); this._loading.set(false); return throwError(() => e); })
    );
  }
  update(id: number, dto: CompteCreateRequest): Observable<Compte> {
    this._loading.set(true);
    return this.api.updateCompte(id, dto).pipe(
      tap(u => { this._comptes.update(l => l.map(c => c.id === id ? u : c)); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur modification'); this._loading.set(false); return throwError(() => e); })
    );
  }
  delete(id: number): Observable<void> {
    this._loading.set(true);
    return this.api.deleteCompte(id).pipe(
      tap(() => { this._comptes.update(l => l.filter(c => c.id !== id)); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur suppression'); this._loading.set(false); return throwError(() => e); })
    );
  }
  getById(id: number): Compte | undefined { return this._comptes().find(c => c.id === id); }
  clear(): void { this._comptes.set([]); this._error.set(null); }
}
