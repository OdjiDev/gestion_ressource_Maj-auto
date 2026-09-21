import { Injectable, inject, signal, computed } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { CategorieDto } from '../models/categorie.model';
import { CategorieService } from './categorie-api.service';

@Injectable({ providedIn: 'root' })
export class CategorieStore {
  private readonly api = inject(CategorieService);
  private readonly _categories = signal<CategorieDto[]>([]);
  private readonly _loading = signal(false);
  private readonly _error = signal<string | null>(null);

  readonly categories = this._categories.asReadonly();
  readonly loading = this._loading.asReadonly();
  readonly error = this._error.asReadonly();
  readonly isEmpty = computed(() => this._categories().length === 0);
  readonly total = computed(() => this._categories().length);

  loadAll(force = false): void {
    if (!force && this._categories().length > 0 && !this._loading()) return;
    this._loading.set(true);
    this._error.set(null);
    this.api.getAllCategories().subscribe({
      next: (data) => { this._categories.set(data); this._loading.set(false); },
      error: (err) => { this._error.set('Erreur chargement'); this._loading.set(false); console.error(err); }
    });
  }

  refresh(): void { this.loadAll(true); }

  create(dto: CategorieDto): Observable<CategorieDto> {
    this._loading.set(true);
    return this.api.addCategorie(dto).pipe(
      tap((created) => { this._categories.update(l => [...l, created]); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur création'); this._loading.set(false); return throwError(() => err); })
    );
  }

  update(id: number, dto: CategorieDto): Observable<CategorieDto> {
    this._loading.set(true);
    return this.api.updateCategorie(id, dto).pipe(
      tap((updated) => { this._categories.update(l => l.map(c => c.id === id ? updated : c)); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur modification'); this._loading.set(false); return throwError(() => err); })
    );
  }

  delete(id: number): Observable<void> {
    this._loading.set(true);
    return this.api.deleteCategorie(id).pipe(
      tap(() => { this._categories.update(l => l.filter(c => c.id !== id)); this._loading.set(false); }),
      catchError((err) => { this._error.set('Erreur suppression'); this._loading.set(false); return throwError(() => err); })
    );
  }

  getById(id: number): CategorieDto | undefined { return this._categories().find(c => c.id === id); }
  clear(): void { this._categories.set([]); this._error.set(null); }
}
