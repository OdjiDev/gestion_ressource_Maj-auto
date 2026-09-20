import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CategorieService } from '@features/categories';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-categorie-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './categorie-create.html',
  styleUrl: './categorie-create.scss'
})
export class CategorieCreate {
  private readonly categorieService = inject(CategorieService);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Enregistrer',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', placeholder: 'Ex: CAT-001', required: true },
      {
        name: 'nomcategorie',
        label: 'Nom de la catégorie',
        type: 'text',
        placeholder: 'Ex: Outillage',
        required: true
      },
      {
        name: 'designation',
        label: 'Description',
        type: 'textarea',
        placeholder: 'Description...',
        colSpan: 2
      }
    ]
  };

  onSave(data: any): void {
    this.loading.set(true);
    this.error.set(null);
    this.categorieService.addCategorie(data).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['/categories']);
      },
      error: err => {
        console.error(err);
        this.error.set('Erreur lors de la création');
        this.loading.set(false);
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/categories']);
  }
}
