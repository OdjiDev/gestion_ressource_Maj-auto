import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Categorie } from '@features/categories';
import { Produit } from '@features/produits';
import { ProduitService } from '@features/produits';
@Component({
  imports: [FormsModule, RouterModule],
  selector: 'app-produit-create',
  styleUrl: './produit-create.scss',
  templateUrl: './produit-create.html',
})
export class ProduitCreate {






  private readonly produitService = inject(ProduitService);
  private readonly router = inject(Router);

  // ==================== STATE ====================
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  // ==================== FORMULAIRE ====================
  produit: Produit = {
    codeproduit: '',
    nom: '',
    designation: '',
    quantite: 0,
    id: 0,
    prixAchat: 0,
  };

  // ==================== SOUMISSION ====================

  onSubmit(): void {
    // Validation
    if (!this.produit.codeproduit || !this.produit.nom) {
      this.error.set('Code produit et Nom sont obligatoires');
      return;
    }

    if (this.produit.quantite < 0) {
      this.error.set('La quantité ne peut pas être négative');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.produitService.addProduit(this.produit).subscribe({
      next: (created: Produit) => {
        this.loading.set(false);
        console.log('Produit créé:', created);
        this.router.navigate(['/produits']);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set('Erreur lors de la création');
        console.error(err);
      }
    });
  }

  // ==================== ANNULATION ====================

  onCancel(): void {
    this.router.navigate(['/produits']);
  }
}
