import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '@features/categories';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-categorie-update',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './categorie-update.html',
  styleUrl: './categorie-update.scss'
})
export class CategorieUpdate implements OnInit {
  private readonly categorieService = inject(CategorieService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly initialData = signal<any>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Modifier',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', placeholder: 'Ex: CAT-001', required: true },
      { name: 'nomcategorie', label: 'Nom de la catégorie', type: 'text', placeholder: 'Ex: Outillage', required: true },
      { name: 'designation', label: 'Description', type: 'textarea', placeholder: 'Description...', colSpan: 2 }
    ]
  };

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) this.loadCategorie(id);
    else this.error.set('ID manquant');
  }

  loadCategorie(id: number): void {
    this.loading.set(true);
    this.categorieService.getCategorieById(id).subscribe({
      next: (data) => { this.initialData.set(data); this.loading.set(false); },
      error: (err) => { this.error.set('Catégorie introuvable'); this.loading.set(false); console.error(err); }
    });
  }

  onSave(data: any): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;
    this.loading.set(true);
    this.error.set(null);
    this.categorieService.updateCategorie(id, data).subscribe({
      next: () => { this.loading.set(false); this.router.navigate(['/categories']); },
      error: (err) => { console.error(err); this.error.set('Erreur lors de la modification'); this.loading.set(false); }
    });
  }

  onCancel(): void { this.router.navigate(['/categories']); }
}
