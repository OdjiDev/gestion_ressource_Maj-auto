import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MouvementStore } from '../../services/mouvement.store';
import { CompteStore } from '../../../compte/services/compte.store';
import {
  TYPE_MOUVEMENT_OPTIONS,
  MODE_PAIEMENT_OPTIONS,
  CATEGORIE_MOUVEMENT_OPTIONS
} from '../../../shared/enums';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-mouvement-update',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './mouvement-update.html',
  styleUrl: './mouvement-update.scss'
})
export class MouvementUpdate implements OnInit {

  readonly store = inject(MouvementStore);
  private readonly compteStore = inject(CompteStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly initialData = signal<any>(null);
  readonly formConfig = signal<CrudFormConfig | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }
    this.loadFormData(id);
  }

  private loadFormData(id: number): void {
    let mouvement = this.store.getById(id);
    if (!mouvement) {
      this.store.loadAll();
      setTimeout(() => {
        mouvement = this.store.getById(id);
        if (mouvement) this.initialData.set(mouvement);
        else this.error.set('Mouvement introuvable');
      }, 300);
    } else {
      this.initialData.set(mouvement);
    }

    this.compteStore.loadAll();
    setTimeout(() => {
      this.formConfig.set(this.buildConfig());
    }, 300);
  }

  private buildConfig(): CrudFormConfig {
    const comptes = this.compteStore.comptesActifs();
    return {
      submitLabel: 'Modifier',
      cancelLabel: 'Annuler',
      fields: [
        { name: 'dateOperation', label: 'Date', type: 'date', required: true },
        { name: 'type', label: 'Type', type: 'select', required: true, options: TYPE_MOUVEMENT_OPTIONS },
        { name: 'montant', label: 'Montant (FCFA)', type: 'number', required: true },
        { name: 'categorie', label: 'Catégorie', type: 'select', required: true, options: CATEGORIE_MOUVEMENT_OPTIONS },
        { name: 'modePaiement', label: 'Mode de paiement', type: 'select', required: true, options: MODE_PAIEMENT_OPTIONS },
        {
          name: 'compteId', label: 'Compte', type: 'select', required: true,
          options: (comptes ?? []).map(c => ({ value: c.id, label: `${c.nom} (${c.devise})` }))
        },
        { name: 'motif', label: 'Motif', type: 'textarea', required: true, colSpan: 2 },
        { name: 'reference', label: 'Référence', type: 'text', colSpan: 2 }
      ]
    };
  }

  onSave(data: any): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;
    this.error.set(null);
    this.store.update(id, data).subscribe({
      next: () => this.router.navigate(['/tresorerie/mouvements']),
      error: (err) => { this.error.set(err?.error?.message || 'Erreur modification'); }
    });
  }

  onCancel(): void { this.router.navigate(['/tresorerie/mouvements']); }
}
