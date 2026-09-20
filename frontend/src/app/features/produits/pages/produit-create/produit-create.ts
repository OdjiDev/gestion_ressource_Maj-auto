import { Component, OnInit, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CategorieService } from '@features/categories';
import { MagasinService } from '@features/affectations';
import { ProduitService } from '@features/produits';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-produit-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './produit-create.html',
  styleUrl: './produit-create.scss'
})
export class ProduitCreate implements OnInit {
  private readonly produitService = inject(ProduitService);
  private readonly categorieService = inject(CategorieService);
  private readonly magasinService = inject(MagasinService);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly formConfig = signal<CrudFormConfig | null>(null);

  ngOnInit(): void {
    this.loadFormData();
  }

  private loadFormData(): void {
    this.loading.set(true);
    this.categorieService.getCategories().subscribe({
      next: categories => {
        this.magasinService.getMagasins().subscribe({
          next: magasins => {
            this.formConfig.set(this.buildConfig(categories, magasins));
            this.loading.set(false);
          },
          error: err => {
            console.error(err);
            this.error.set('Erreur chargement magasins');
            this.loading.set(false);
          }
        });
      },
      error: err => {
        console.error(err);
        this.error.set('Erreur chargement catégories');
        this.loading.set(false);
      }
    });
  }

  private buildConfig(categories: any[], magasins: any[]): CrudFormConfig {
    return {
      submitLabel: 'Enregistrer',
      cancelLabel: 'Annuler',
      fields: [
        {
          name: 'codeproduit',
          label: 'Code produit',
          type: 'text',
          placeholder: 'Ex: VIS-M6',
          required: true
        },
        { name: 'nom', label: 'Nom', type: 'text', placeholder: 'Ex: Vis M6', required: true },
        {
          name: 'designation',
          label: 'Désignation',
          type: 'textarea',
          placeholder: 'Description...',
          colSpan: 2
        },
        {
          name: 'categorieId',
          label: 'Catégorie',
          type: 'select',
          required: true,
          options: categories.map(c => ({ value: c.id, label: c.nomcategorie }))
        },
        {
          name: 'magasinId',
          label: 'Magasin',
          type: 'select',
          options: magasins.map(m => ({ value: m.id, label: m.nom }))
        },
        { name: 'quantite', label: 'Quantité', type: 'number', defaultValue: 0 },
        { name: 'prixAchat', label: "Prix d'achat", type: 'number', defaultValue: 0 }
      ]
    };
  }

  onSave(data: any): void {
    this.loading.set(true);
    this.error.set(null);
    this.produitService.addProduit(data).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['/produits']);
      },
      error: err => {
        console.error(err);
        this.error.set('Erreur création');
        this.loading.set(false);
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/produits']);
  }
}
