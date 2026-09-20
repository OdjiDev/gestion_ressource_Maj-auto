import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieStore } from '@features/categories';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-categorie-update',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './categorie-update.html',
  styleUrl: './categorie-update.scss'
})
export class CategorieUpdate implements OnInit {

  readonly store = inject(CategorieStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly initialData = signal<any>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Modifier',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', placeholder: 'Ex: CAT-001', required: true },
      { name: 'nom', label: 'Nom', type: 'text', placeholder: 'Ex: Outillage', required: true },
      { name: 'designation', label: 'Description', type: 'textarea', placeholder: 'Description...', colSpan: 2 }
    ]
  };

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }

    // Essaie d'abord le cache du store
    const cached = this.store.getById(id);
    if (cached) {
      this.initialData.set(cached);
      return;
    }

    // Sinon charge depuis l'API
    this.store.loadAll();
    setTimeout(() => {
      const cat = this.store.getById(id);
      if (cat) this.initialData.set(cat);
      else this.error.set('Catégorie introuvable');
    }, 500);
  }

  onSave(data: any): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;

    this.error.set(null);
    this.store.update(id, data).subscribe({
      next: () => this.router.navigate(['/categories']),
      error: () => this.error.set('Erreur lors de la modification')
    });
  }

  onCancel(): void {
    this.router.navigate(['/categories']);
  }
}
