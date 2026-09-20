import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CategorieDto, CategorieService } from '@features/categories';
import { ColumnDef, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent } from '@shared/components';

@Component({
  selector: 'app-categorie-list',
  standalone: true,
  imports: [CommonModule, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent],
  templateUrl: './categorie-list.html',
  styleUrl: './categorie-list.scss'
})
export class CategorieList implements OnInit {
  private readonly categorieService = inject(CategorieService);
  private readonly router = inject(Router);

  readonly categories = signal<CategorieDto[]>([]);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly showConfirm = signal(false);
  readonly categorieToDelete = signal<CategorieDto | null>(null);

  readonly columns: ColumnDef<CategorieDto>[] = [
    { key: 'id', label: 'ID', width: '60px', align: 'center' },
    { key: 'code', label: 'Code', sortable: true },
    { key: 'nomcategorie', label: 'Nom', sortable: true },
    { key: 'designation', label: 'Désignation' }
  ];

  ngOnInit(): void { this.loadCategories(); }

  loadCategories(): void {
    this.loading.set(true);
    this.error.set(null);
    this.categorieService.getCategories().subscribe({
      next: (data) => { this.categories.set(data); this.loading.set(false); },
      error: (err) => { this.error.set('Erreur lors du chargement'); this.loading.set(false); console.error(err); }
    });
  }

  onCreate(): void { this.router.navigate(['/categories/create']); }
  onEdit(categorie: CategorieDto): void { this.router.navigate(['/categories/update', categorie.id]); }
  onDelete(categorie: CategorieDto): void { this.categorieToDelete.set(categorie); this.showConfirm.set(true); }

  confirmDelete(): void {
    const categorie = this.categorieToDelete();
    if (!categorie?.id) return;
    this.categorieService.deleteCategorie(categorie.id).subscribe({
      next: () => { this.categories.update(list => list.filter(c => c.id !== categorie.id)); this.closeConfirm(); },
      error: (err) => { this.error.set('Erreur lors de la suppression'); this.closeConfirm(); console.error(err); }
    });
  }

  closeConfirm(): void { this.showConfirm.set(false); this.categorieToDelete.set(null); }
}
