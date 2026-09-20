import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Produit, ProduitService } from '@features/produits';
import {
  ColumnDef,
  DataTableComponent,
  PageHeaderComponent,
  ConfirmDialogComponent
} from '@shared/components';

@Component({
  selector: 'app-produit-list',
  standalone: true,
  imports: [CommonModule, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent],
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

  // Confirm dialog state
  readonly showConfirm = signal(false);
  readonly produitToDelete = signal<Produit | null>(null);

  // ==================== CONFIG TABLE ====================
  readonly columns: ColumnDef<Produit>[] = [
    { key: 'id', label: 'ID', width: '60px', align: 'center' },
    { key: 'codeproduit', label: 'Code', sortable: true },
    { key: 'nom', label: 'Nom', sortable: true },
    { key: 'designation', label: 'Désignation' },
    { key: 'quantite', label: 'Quantité', align: 'right', sortable: true },
    { key: 'categorieNom', label: 'Catégorie', type: 'badge' }
  ];

  // ==================== LIFECYCLE ====================
  ngOnInit(): void {
    this.loadProduits();
  }

  // ==================== LECTURE ====================
  loadProduits(): void {
    this.loading.set(true);
    this.error.set(null);

    this.produitService.getProduits().subscribe({
      next: data => {
        this.produits.set(data);
        this.loading.set(false);
      },
      error: err => {
        this.error.set('Erreur lors du chargement');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  // ==================== ACTIONS ====================
  onCreate(): void {
    this.router.navigate(['/produits/create']);
  }

  onEdit(produit: Produit): void {
    this.router.navigate(['/produits/update', produit.id]);
  }

  onDetails(produit: Produit): void {
    this.router.navigate(['/produits/detail', produit.id]);
  }

  onDelete(produit: Produit): void {
    this.produitToDelete.set(produit);
    this.showConfirm.set(true);
  }

  confirmDelete(): void {
    const produit = this.produitToDelete();
    if (!produit?.id) return;

    this.produitService.deleteProduit(produit.id).subscribe({
      next: () => {
        this.produits.update(list => list.filter(p => p.id !== produit.id));
        this.closeConfirm();
      },
      error: err => {
        this.error.set('Erreur lors de la suppression');
        this.closeConfirm();
        console.error(err);
      }
    });
  }

  closeConfirm(): void {
    this.showConfirm.set(false);
    this.produitToDelete.set(null);
  }
}
