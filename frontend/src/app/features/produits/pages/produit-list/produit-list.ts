import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Produit, ProduitStore } from '@features/produits';
import { ColumnDef, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent } from '@shared/components';

@Component({
  selector: 'app-produit-list',
  standalone: true,
  imports: [CommonModule, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent],
  templateUrl: './produit-list.html',
  styleUrl: './produit-list.scss'
})
export class ProduitList implements OnInit {
  private readonly router = inject(Router);
  readonly store = inject(ProduitStore);

  readonly showConfirm = signal(false);
  readonly produitToDelete = signal<Produit | null>(null);

  readonly columns: ColumnDef<Produit>[] = [
    { key: 'id', label: 'ID', width: '60px', align: 'center' },
    { key: 'codeproduit', label: 'Code', sortable: true },
    { key: 'nom', label: 'Nom', sortable: true },
    { key: 'designation', label: 'Désignation' },
    { key: 'quantite', label: 'Quantité', align: 'right', sortable: true },
    { key: 'categorieNom', label: 'Catégorie', type: 'badge' }
  ];

  ngOnInit(): void { this.store.loadAll(); }
  onCreate(): void { this.router.navigate(['/produits/create']); }
  onEdit(p: Produit): void { this.router.navigate(['/produits/update', p.id]); }
  onDetails(p: Produit): void { this.router.navigate(['/produits/detail', p.id]); }
  onDelete(p: Produit): void { this.produitToDelete.set(p); this.showConfirm.set(true); }

  confirmDelete(): void {
    const p = this.produitToDelete();
    if (!p?.id) return;
    this.store.delete(p.id).subscribe({
      next: () => this.closeConfirm(),
      error: () => this.closeConfirm()
    });
  }
  closeConfirm(): void { this.showConfirm.set(false); this.produitToDelete.set(null); }
}
