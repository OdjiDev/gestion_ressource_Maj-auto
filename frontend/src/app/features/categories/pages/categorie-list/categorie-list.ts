import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CategorieDto, CategorieStore } from '@features/categories';
import {
  ColumnDef,
  DataTableComponent,
  PageHeaderComponent,
  ConfirmDialogComponent
} from '@shared/components';

@Component({
  selector: 'app-categorie-list',
  standalone: true,
  imports: [CommonModule, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent],
  templateUrl: './categorie-list.html',
  styleUrl: './categorie-list.scss'
})
export class CategorieList implements OnInit {

  private readonly router = inject(Router);
  readonly store = inject(CategorieStore);

  readonly showConfirm = signal(false);
  readonly categorieToDelete = signal<CategorieDto | null>(null);

  readonly columns: ColumnDef<CategorieDto>[] = [
    { key: 'id', label: 'ID', width: '60px', align: 'center' },
    { key: 'code', label: 'Code', sortable: true },
    { key: 'nom', label: 'Nom', sortable: true },
    { key: 'designation', label: 'Désignation' }
  ];

  ngOnInit(): void {
    this.store.loadAll();
  }

  onCreate(): void { this.router.navigate(['/categories/create']); }
  onEdit(cat: CategorieDto): void { this.router.navigate(['/categories/update', cat.id]); }
  onDelete(cat: CategorieDto): void { this.categorieToDelete.set(cat); this.showConfirm.set(true); }

  confirmDelete(): void {
    const cat = this.categorieToDelete();
    if (!cat?.id) return;
    this.store.delete(cat.id).subscribe({
      next: () => this.closeConfirm(),
      error: () => this.closeConfirm()
    });
  }

  closeConfirm(): void { this.showConfirm.set(false); this.categorieToDelete.set(null); }
}
