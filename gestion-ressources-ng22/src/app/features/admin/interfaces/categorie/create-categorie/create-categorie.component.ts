import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CategorieService } from '@core/services/categorie.service';
import { CategorieDto } from '@core/models/categorie-dto';

@Component({
  selector: 'app-create-categorie',
  // standalone: true est implicite en Angular 22 (défaut)
  imports: [FormsModule, RouterModule],
  templateUrl: './create-categorie.component.html',
  styleUrls: ['./create-categorie.component.css']
})
export class CreateCategorieComponent {

  private readonly categorieService = inject(CategorieService);
  private readonly router = inject(Router);

  categorieDto: CategorieDto = {
    nomcategorie: '',
    code: '',
    designation: '',
    id: 0
  };

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  onSubmit(): void {
    if (!this.categorieDto.nomcategorie || !this.categorieDto.code) {
      this.error.set('Nom et Code sont obligatoires');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.categorieService.addCategorie(this.categorieDto).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['/admin/categories']);
      },
      error: (err: any) => {
        this.loading.set(false);
        this.error.set('Erreur lors de la création');
        console.error(err);
      }
    });
  }
}
