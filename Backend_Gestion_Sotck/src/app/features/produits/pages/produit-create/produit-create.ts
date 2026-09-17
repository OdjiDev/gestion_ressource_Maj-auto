import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CategorieDto } from '@app/features/categories/categorie.model';
import { LigneFactureDto } from '@app/core/models/lignefacture-dto';
import { ProduitDto } from '@core/models/produit-dto';
import { ProduitService } from '@core/services/produit.service';
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
  produit: ProduitDto = {
    codeproduit: '',
    nom: '',
    designation: '',
    quantite: 0,
    id: 0,
    prixAchat: 0,
    categorieDto: new CategorieDto,
    ligneFactureDto: new LigneFactureDto
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
      next: (created: ProduitDto) => {
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
