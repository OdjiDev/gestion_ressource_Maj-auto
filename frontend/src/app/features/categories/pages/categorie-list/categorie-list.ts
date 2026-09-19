import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CategorieService } from '@features/categories';
import { CategorieDto } from '@features/categories';

@Component({
  selector: 'app-categorie-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './categorie-list.html',
  styleUrl: './categorie-list.scss'
})
export class CategorieList implements OnInit {

  private readonly categorieService = inject(CategorieService);
  private readonly router = inject(Router);

  readonly categories = signal<CategorieDto[]>([]);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.loading.set(true);
    this.error.set(null);

    this.categorieService.getCategories().subscribe({
      next: (data: CategorieDto[]) => {
        this.categories.set(data);
        this.loading.set(false);
        console.log('Catégories chargées:', data);
      },
      error: (err) => {
        this.error.set('Erreur lors du chargement');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  onCreateCategorie(): void {
    this.router.navigate(['/categories/create']);
  }

  onEdit(id: number): void {
    this.router.navigate(['/categories/update', id]);
  }

  onDelete(id: number): void {
    if (!confirm('Supprimer cette catégorie ?')) {
      return;
    }

    this.categorieService.deleteCategorie(id).subscribe({
      next: () => {
        this.categories.update(list => list.filter(c => c.id !== id));
        console.log('Catégorie supprimée:', id);
      },
      error: (err) => {
        this.error.set('Erreur lors de la suppression');
        console.error(err);
      }
    });
  }
}
