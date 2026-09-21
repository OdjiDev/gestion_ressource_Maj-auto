import { Injectable, inject, signal, computed } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { Produit } from '../models/produit.model';
import { ProduitService } from './produit-api.service';

@Injectable({ providedIn: 'root' })
export class ProduitStore {
  private readonly api = inject(ProduitService);
  private readonly _produits = signal<Produit[]>([]);
  private readonly _loading = signal(false);
  private readonly _error = signal<string | null>(null);

  readonly produits = this._produits.asReadonly();
  readonly loading = this._loading.asReadonly();
  readonly error = this._error.asReadonly();
  readonly isEmpty = computed(() => this._produits().length === 0);
  readonly total = computed(() => this._produits().length);

  loadAll(force = false): void {
    if (!force && this._produits().length > 0 && !this._loading()) return;
    this._loading.set(true);
    this._error.set(null);
    this.api.getProduits().subscribe({
      next: (d) => { this._produits.set(d); this._loading.set(false); },
      error: (e) => { this._error.set('Erreur chargement'); this._loading.set(false); console.error(e); }
    });
  }
  refresh(): void { this.loadAll(true); }

  create(dto: Produit): Observable<Produit> {
    this._loading.set(true);
    this._error.set(null);
    return this.api.addProduit(dto).pipe(
      tap(c => { this._produits.update(l => [...l, c]); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur création'); this._loading.set(false); return throwError(() => e); })
    );
  }
  update(id: number, dto: Produit): Observable<Produit> {
    this._loading.set(true);
    this._error.set(null);
    return this.api.updateProduit(id, dto).pipe(
      tap(u => { this._produits.update(l => l.map(p => p.id === id ? u : p)); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur modification'); this._loading.set(false); return throwError(() => e); })
    );
  }
  delete(id: number): Observable<Produit> {
    this._loading.set(true);
    this._error.set(null);
    return this.api.deleteProduit(id).pipe(
      tap(() => { this._produits.update(l => l.filter(p => p.id !== id)); this._loading.set(false); }),
      catchError(e => { this._error.set('Erreur suppression'); this._loading.set(false); return throwError(() => e); })
    );
  }
  getById(id: number): Produit | undefined { return this._produits().find(p => p.id === id); }
  clear(): void { this._produits.set([]); this._error.set(null); }
}
