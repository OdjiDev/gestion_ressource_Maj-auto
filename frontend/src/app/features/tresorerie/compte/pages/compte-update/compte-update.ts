import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompteStore } from '../../services/compte.store';
import { TYPE_COMPTE_OPTIONS } from '../../../shared/enums';
import { CrudFormComponent, CrudFormConfig, PageHeaderComponent } from '@shared/components';

@Component({
  selector: 'app-compte-update',
  standalone: true,
  imports: [CrudFormComponent, PageHeaderComponent],
  templateUrl: './compte-update.html',
  styleUrl: './compte-update.scss'
})
export class CompteUpdate implements OnInit {
  readonly store = inject(CompteStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly error = signal<string | null>(null);
  readonly initialData = signal<any>(null);

  readonly formConfig: CrudFormConfig = {
    submitLabel: 'Modifier',
    cancelLabel: 'Annuler',
    fields: [
      { name: 'code', label: 'Code', type: 'text', required: true },
      { name: 'nom', label: 'Nom', type: 'text', required: true },
      { name: 'type', label: 'Type de compte', type: 'select', required: true, options: TYPE_COMPTE_OPTIONS },
      { name: 'devise', label: 'Devise', type: 'text', required: true },
      { name: 'soldeInitial', label: 'Solde initial', type: 'number' },
      { name: 'seuilAlerte', label: "Seuil d'alerte", type: 'number' },
      { name: 'actif', label: 'Actif', type: 'checkbox' }
    ]
  };

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }
    let compte = this.store.getById(id);
    if (compte) { this.initialData.set(compte); return; }
    this.store.loadAll();
    setTimeout(() => {
      compte = this.store.getById(id);
      if (compte) this.initialData.set(compte);
      else this.error.set('Compte introuvable');
    }, 300);
  }

  onSave(data: any): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) return;
    this.error.set(null);
    this.store.update(id, data).subscribe({
      next: () => this.router.navigate(['/tresorerie/comptes']),
      error: (err) => { this.error.set(err?.error?.message || 'Erreur modification'); }
    });
  }
  onCancel(): void { this.router.navigate(['/tresorerie/comptes']); }
}
