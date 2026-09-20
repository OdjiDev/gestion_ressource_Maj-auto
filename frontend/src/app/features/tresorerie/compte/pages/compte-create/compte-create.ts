import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CompteStore } from '../../services/compte.store';
import { TYPE_COMPTE_OPTIONS } from '../../../shared/enums';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-compte-create',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './compte-create.html',
  styleUrl: './compte-create.scss'
})
export class CompteCreate {
  readonly store = inject(CompteStore);
  private readonly router = inject(Router);
  readonly error = signal<string | null>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Enregistrer',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', placeholder: 'Ex: CAISSE-001', required: true },
      { name: 'nom', label: 'Nom', type: 'text', placeholder: 'Ex: Caisse principale', required: true },
      { name: 'type', label: 'Type de compte', type: 'select', required: true, options: TYPE_COMPTE_OPTIONS },
      { name: 'devise', label: 'Devise', type: 'text', defaultValue: 'FCFA', required: true },
      { name: 'soldeInitial', label: 'Solde initial', type: 'number', defaultValue: 0 },
      { name: 'seuilAlerte', label: "Seuil d'alerte", type: 'number', placeholder: 'Optionnel' },
      { name: 'actif', label: 'Actif', type: 'checkbox', defaultValue: true, placeholder: 'Compte actif' }
    ]
  };

  onSave(data: any): void {
    this.error.set(null);
    this.store.create(data).subscribe({
      next: () => this.router.navigate(['/tresorerie/comptes']),
      error: (err) => {
        const msg = err?.error?.message || err?.error?.error || 'Erreur lors de la création';
        this.error.set(msg);
      }
    });
  }

  onCancel(): void { this.router.navigate(['/tresorerie/comptes']); }
}
