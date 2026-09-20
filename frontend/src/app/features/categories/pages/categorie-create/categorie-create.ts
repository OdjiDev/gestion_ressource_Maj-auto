import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CategorieStore } from '@features/categories';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-categorie-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './categorie-create.html',
  styleUrl: './categorie-create.scss'
})
export class CategorieCreate {

  readonly store = inject(CategorieStore);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Enregistrer',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', placeholder: 'Ex: CAT-001', required: true },
      { name: 'nom', label: 'Nom', type: 'text', placeholder: 'Ex: Outillage', required: true },
      { name: 'designation', label: 'Description', type: 'textarea', placeholder: 'Description...', colSpan: 2 }
    ]
  };

  onSave(data: any): void {
    this.error.set(null);
    this.store.create(data).subscribe({
      next: () => this.router.navigate(['/categories']),
      error: () => this.error.set('Erreur lors de la création')
    });
  }

  onCancel(): void {
    this.router.navigate(['/categories']);
  }
}
