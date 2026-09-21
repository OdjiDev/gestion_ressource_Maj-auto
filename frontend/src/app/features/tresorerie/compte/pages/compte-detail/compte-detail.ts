import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CompteStore } from '../../services/compte.store';
import { CompteApiService } from '../../services/compte-api.service';
import { PageHeaderComponent, LoadingSpinnerComponent } from '@shared/components';

@Component({
  selector: 'app-compte-detail',
  standalone: true,
  imports: [CommonModule, PageHeaderComponent, LoadingSpinnerComponent],
  templateUrl: './compte-detail.html',
  styleUrl: './compte-detail.scss'
})
export class CompteDetail implements OnInit {
  private readonly store = inject(CompteStore);
  private readonly api = inject(CompteApiService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly compte = signal<any>(null);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }
    const cached = this.store.getById(id);
    if (cached) { this.compte.set(cached); return; }
    this.loading.set(true);
    this.api.getCompteById(id).subscribe({
      next: (d) => { this.compte.set(d); this.loading.set(false); },
      error: (e) => { this.error.set('Compte introuvable'); this.loading.set(false); console.error(e); }
    });
  }
  onBack(): void { this.router.navigate(['/tresorerie/comptes']); }
  onEdit(): void { const c = this.compte(); if (c?.id) this.router.navigate(['/tresorerie/comptes/update', c.id]); }
}
