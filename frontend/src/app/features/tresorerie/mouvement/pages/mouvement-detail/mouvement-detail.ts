import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MouvementStore } from '../../services/mouvement.store';
import { MouvementApiService } from '../../services/mouvement-api.service';
import { PageHeaderComponent, LoadingSpinnerComponent } from '@shared/components';

@Component({
  selector: 'app-mouvement-detail',
  standalone: true,
  imports: [CommonModule, PageHeaderComponent, LoadingSpinnerComponent],
  templateUrl: './mouvement-detail.html',
  styleUrl: './mouvement-detail.scss'
})
export class MouvementDetail implements OnInit {

  private readonly store = inject(MouvementStore);
  private readonly api = inject(MouvementApiService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  readonly mouvement = signal<any>(null);
  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error.set('ID manquant'); return; }

    const cached = this.store.getById(id);
    if (cached) { this.mouvement.set(cached); return; }

    this.loading.set(true);
    this.api.getMouvementById(id).subscribe({
      next: (d) => { this.mouvement.set(d); this.loading.set(false); },
      error: (e) => { this.error.set('Mouvement introuvable'); this.loading.set(false); console.error(e); }
    });
  }

  onBack(): void { this.router.navigate(['/tresorerie/mouvements']); }
  onEdit(): void {
    const m = this.mouvement();
    if (m?.id) this.router.navigate(['/tresorerie/mouvements/update', m.id]);
  }
}
