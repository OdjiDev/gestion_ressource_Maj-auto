import { Injectable, signal } from '@angular/core';
import { Produit } from '../models/produit.model';

@Injectable({ providedIn: 'root' })
export class ProduitStore {
  readonly produits = signal<Produit[]>([]);
  readonly loading = signal(false);
}
