import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CategorieService } from '@features/categories';
import { CategorieDto } from '@features/categories';

@Component({
  selector: 'app-categorie-create',
  imports: [FormsModule, RouterModule],
  templateUrl: './categorie-create.html',
  styleUrl: './categorie-create.scss'
})
export class CategorieCreate {

  private readonly categorieService = inject(CategorieService);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  categorie: CategorieDto = {
    nomcategorie: '',
    code: '',
    designation: '',
    id: 0
  };

  onSubmit(): void {
    if (!this.categorie.nomcategorie || !this.categorie.code) {
      this.error.set('Nom et Code sont obligatoires');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.categorieService.addCategorie(this.categorie).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['/categories']);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set('Erreur lors de la création');
        console.error(err);
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/categories']);
  }
}
