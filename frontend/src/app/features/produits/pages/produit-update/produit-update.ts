import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '@features/categories';
import { MagasinService } from '@features/affectations';
import { ProduitStore } from '@features/produits';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-produit-update',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './produit-update.html',
  styleUrl: './produit-update.scss'
})
export class ProduitUpdate implements OnInit {
  readonly store = inject(ProduitStore);
  private readonly categorieService = inject(CategorieService);
  private readonly magasinService = inject(MagasinService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly formConfig = signal<CrudFormConfig | null>(null);
  readonly initialData = signal<any>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }
    this.loadFormData(id);
  }

  private loadFormData(id: number): void {
    let produit = this.store.getById(id);
    if (!produit) {
      this.store.loadAll();
      setTimeout(() => {
        produit = this.store.getById(id);
        if (!produit) { this.error.set('Produit introuvable'); return; }
        this.initialData.set(produit);
      }, 300);
    } else {
      this.initialData.set(produit);
    }

    this.categorieService.getAllCategories().subscribe({
      next: (categories) => {
        this.magasinService.getMagasins().subscribe({
          next: (magasins) => { this.formConfig.set(this.buildConfig(categories, magasins)); },
          error: (err) => { console.error(err); this.error.set('Erreur magasins'); }
        });
      },
      error: (err) => { console.error(err); this.error.set('Erreur catégories'); }
    });
  }

  private buildConfig(categories: any[], magasins: any[]): CrudFormConfig {
    return {
      submitLabel: 'Modifier',
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
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;
    this.error.set(null);
    this.store.update(id, data).subscribe({
      next: () => this.router.navigate(['/produits']),
      error: () => this.error.set('Erreur modification')
    });
  }

  onCancel(): void { this.router.navigate(['/produits']); }
}
