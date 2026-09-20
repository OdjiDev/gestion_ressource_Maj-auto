import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CompteStore } from '../../services/compte.store';
import { Compte } from '../../models/compte.model';
import { ColumnDef, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent } from '@shared/components';

@Component({
  selector: 'app-compte-list',
  standalone: true,
  imports: [CommonModule, DataTableComponent, PageHeaderComponent, ConfirmDialogComponent],
  templateUrl: './compte-list.html',
  styleUrl: './compte-list.scss'
})
export class CompteList implements OnInit {
  private readonly router = inject(Router);
  readonly store = inject(CompteStore);

  readonly showConfirm = signal(false);
  readonly compteToDelete = signal<Compte | null>(null);

  readonly columns: ColumnDef<Compte>[] = [
    { key: 'id', label: 'ID', width: '60px', align: 'center' },
    { key: 'code', label: 'Code', sortable: true },
    { key: 'nom', label: 'Nom', sortable: true },
    { key: 'type', label: 'Type', type: 'badge' },
    { key: 'devise', label: 'Devise', width: '80px', align: 'center' },
    { key: 'soldeInitial', label: 'Solde initial', align: 'right' },
    { key: 'soldeActuel', label: 'Solde actuel', align: 'right' }
  ];

  ngOnInit(): void { this.store.loadAll(); }
  onCreate(): void { this.router.navigate(['/tresorerie/comptes/create']); }
  onEdit(c: Compte): void { this.router.navigate(['/tresorerie/comptes/update', c.id]); }
  onDetail(c: Compte): void { this.router.navigate(['/tresorerie/comptes/detail', c.id]); }
  onDelete(c: Compte): void { this.compteToDelete.set(c); this.showConfirm.set(true); }

  confirmDelete(): void {
    const c = this.compteToDelete();
    if (!c?.id) return;
    this.store.delete(c.id).subscribe({ next: () => this.closeConfirm(), error: () => this.closeConfirm() });
  }
  closeConfirm(): void { this.showConfirm.set(false); this.compteToDelete.set(null); }
}
