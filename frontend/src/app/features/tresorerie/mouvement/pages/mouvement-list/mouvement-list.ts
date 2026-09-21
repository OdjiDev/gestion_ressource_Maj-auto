import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MouvementStore } from '../../services/mouvement.store';
import { Mouvement } from '../../models/mouvement.model';
import { SoldeCardComponent } from '../../components/solde-card/solde-card.component';
import { TypeBadgeComponent } from '../../components/type-badge/type-badge.component';
import {
  ColumnDef,
  DataTableComponent,
  PageHeaderComponent,
  ConfirmDialogComponent
} from '@shared/components';

@Component({
  selector: 'app-mouvement-list',
  standalone: true,
  imports: [
    CommonModule,
    DataTableComponent,
    PageHeaderComponent,
    ConfirmDialogComponent,
    SoldeCardComponent,
    TypeBadgeComponent
  ],
  templateUrl: './mouvement-list.html',
  styleUrl: './mouvement-list.scss'
})
export class MouvementList implements OnInit {

  private readonly router = inject(Router);
  readonly store = inject(MouvementStore);

  readonly showConfirm = signal(false);
  readonly mouvementToDelete = signal<Mouvement | null>(null);

  readonly columns: ColumnDef<Mouvement>[] = [
    { key: 'dateOperation', label: 'Date', width: '110px' },
    { key: 'type', label: 'Type', width: '100px', align: 'center' },
    { key: 'categorie', label: 'Catégorie', width: '130px' },
    { key: 'motif', label: 'Motif' },
    { key: 'compteNom', label: 'Compte', width: '140px' },
    { key: 'modePaiement', label: 'Paiement', width: '120px' },
    { key: 'montant', label: 'Montant', align: 'right', width: '130px' }
  ];

  ngOnInit(): void {
    this.store.loadAll();
  }

  onCreate(): void { this.router.navigate(['/tresorerie/mouvements/create']); }
  onEdit(m: Mouvement): void { this.router.navigate(['/tresorerie/mouvements/update', m.id]); }
  onDetail(m: Mouvement): void { this.router.navigate(['/tresorerie/mouvements/detail', m.id]); }
  onDelete(m: Mouvement): void { this.mouvementToDelete.set(m); this.showConfirm.set(true); }

  confirmDelete(): void {
    const m = this.mouvementToDelete();
    if (!m?.id) return;
    this.store.delete(m.id).subscribe({
      next: () => this.closeConfirm(),
      error: () => this.closeConfirm()
    });
  }

  closeConfirm(): void {
    this.showConfirm.set(false);
    this.mouvementToDelete.set(null);
  }
}
