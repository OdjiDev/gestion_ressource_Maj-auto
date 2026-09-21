import { Component, OnInit, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { MouvementStore } from '../../services/mouvement.store';
import { CompteStore } from '../../../compte/services/compte.store';
import {
  TYPE_MOUVEMENT_OPTIONS,
  MODE_PAIEMENT_OPTIONS,
  CATEGORIE_MOUVEMENT_OPTIONS
} from '../../../shared/enums';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-mouvement-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './mouvement-create.html',
  styleUrl: './mouvement-create.scss'
})
export class MouvementCreate implements OnInit {

  readonly store = inject(MouvementStore);
  private readonly compteStore = inject(CompteStore);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly formConfig = signal<CrudFormConfig | null>(null);

  ngOnInit(): void {
    this.loadFormData();
  }

  private loadFormData(): void {
    this.compteStore.loadAll();
    setTimeout(() => {
      this.formConfig.set(this.buildConfig());
    }, 300);
  }

  private buildConfig(): CrudFormConfig {
    const comptes = this.compteStore.comptesActifs();

    return {
      submitLabel: 'Enregistrer',
      cancelLabel: 'Annuler',
      fields: [
        {
          name: 'dateOperation', label: 'Date', type: 'date', required: true,
          defaultValue: new Date().toISOString().slice(0, 10)
        },
        {
          name: 'type', label: 'Type', type: 'select', required: true,
          options: TYPE_MOUVEMENT_OPTIONS
        },
        {
          name: 'montant', label: 'Montant (FCFA)', type: 'number', required: true,
          defaultValue: 0
        },
        {
          name: 'categorie', label: 'Catégorie', type: 'select', required: true,
          options: CATEGORIE_MOUVEMENT_OPTIONS
        },
        {
          name: 'modePaiement', label: 'Mode de paiement', type: 'select', required: true,
          options: MODE_PAIEMENT_OPTIONS
        },
        {
          name: 'compteId', label: 'Compte', type: 'select', required: true,
          options: (comptes ?? []).map(c => ({ value: c.id, label: `${c.nom} (${c.devise})` }))
        },
        {
          name: 'motif', label: 'Motif', type: 'textarea', required: true,
          placeholder: 'Description du mouvement...', colSpan: 2
        },
        {
          name: 'reference', label: 'Référence', type: 'text',
          placeholder: 'Ex: FAC-2026-001', colSpan: 2
        }
      ]
    };
  }

  onSave(data: any): void {
    this.error.set(null);
    this.store.create(data).subscribe({
      next: () => this.router.navigate(['/tresorerie/mouvements']),
      error: (err) => {
        const msg = err?.error?.message || err?.error?.error || 'Erreur lors de la création';
        this.error.set(msg);
      }
    });
  }

  onCancel(): void { this.router.navigate(['/tresorerie/mouvements']); }
}
