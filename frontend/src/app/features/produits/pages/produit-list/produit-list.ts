import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Produit } from '@features/produits';
import { ProduitService } from '@features/produits';

@Component({
  selector: 'app-produit-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './produit-list.html',
  styleUrl: './produit-list.scss'
})
export class ProduitList implements OnInit {

  private readonly produitService = inject(ProduitService);
  private readonly router = inject(Router);

  // ==================== STATE ====================
  readonly produits = signal<Produit[]>([]);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  // ==================== LIFECYCLE ====================

  ngOnInit(): void {
    this.getProduits();
  }

  // ==================== LECTURE ====================

  getProduits(): void {
    this.loading.set(true);
    this.error.set(null);

    this.produitService.getProduits().subscribe({
      next: (data: Produit[]) => {
        this.produits.set(data);
        this.loading.set(false);
        console.log('Produits chargés:', data);
      },
      error: (err) => {
        this.error.set('Erreur lors du chargement');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  // ==================== ACTIONS ====================

  onCreateProduit(): void {
    this.router.navigate(['/produits/create']);
  }

  onEdit(id: number): void {
    this.router.navigate(['/produits/update', id]);
  }

  onDetails(produit: Produit): void {
    this.router.navigate(['/produits/detail', produit.id]);
  }

  onDelete(id: number): void {
    if (!confirm('Supprimer ce produit ?')) {
      return;
    }

    this.produitService.deleteProduit(id).subscribe({
      next: () => {
        // Retire le produit de la liste sans recharger
        this.produits.update(list => list.filter(p => p.id !== id));
        console.log('Produit supprimé:', id);
      },
      error: (err) => {
        this.error.set('Erreur lors de la suppression');
        console.error(err);
      }
    });
  }
}
