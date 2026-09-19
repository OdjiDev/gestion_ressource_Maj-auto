import { Injectable, signal } from '@angular/core';
import { CategorieDto } from '../models/categorie.model';

@Injectable({ providedIn: 'root' })
export class CategorieStore {
  readonly categories = signal<CategorieDto[]>([]);
  readonly loading = signal(false);
}
