import { Component, OnInit, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CategorieService } from '@features/categories';
import { MagasinService } from '@features/affectations';
import { ProduitStore } from '@features/produits';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-produit-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './produit-create.html',
  styleUrl: './produit-create.scss'
})
export class ProduitCreate implements OnInit {
  readonly store = inject(ProduitStore);
  private readonly categorieService = inject(CategorieService);
  private readonly magasinService = inject(MagasinService);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly formConfig = signal<CrudFormConfig | null>(null);

  ngOnInit(): void { this.loadFormData(); }

  private loadFormData(): void {
    this.categorieService.getAllCategories().subscribe({
      next: (categories) => {
        this.magasinService.getMagasins().subscribe({
          next: (magasins) => { this.formConfig.set(this.buildConfig(categories, magasins)); },
          error: (err) => { console.error(err); this.error.set('Erreur chargement magasins'); }
        });
      },
      error: (err) => { console.error(err); this.error.set('Erreur chargement catégories'); }
    });
  }

  private buildConfig(categories: any[], magasins: any[]): CrudFormConfig {
    return {
      submitLabel: 'Enregistrer',
      cancelLabel: 'Annuler',
      fields: [
        { name: 'codeproduit', label: 'Code produit', type: 'text', required: true },
        { name: 'nom', label: 'Nom', type: 'text', required: true },
        { name: 'designation', label: 'Désignation', type: 'textarea', colSpan: 2 },
        { name: 'categorieId', label: 'Catégorie', type: 'select', required: true, options: (categories ?? []).map(c => ({ value: c.id, label: c.nom })) },
        { name: 'magasinId', label: 'Magasin', type: 'select', options: (magasins ?? []).map(m => ({ value: m.id, label: m.nom })) },
        { name: 'quantite', label: 'Quantité', type: 'number', defaultValue: 0 },
        { name: 'prixAchat', label: "Prix d'achat", type: 'number', defaultValue: 0 }
      ]
    };
  }

  onSave(data: any): void {
    this.error.set(null);
    this.store.create(data).subscribe({
      next: () => this.router.navigate(['/produits']),
      error: () => this.error.set('Erreur lors de la création')
    });
  }

  onCancel(): void { this.router.navigate(['/produits']); }
}
