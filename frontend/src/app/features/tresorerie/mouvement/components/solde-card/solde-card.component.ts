import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-solde-card',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="solde-card">
      <div class="solde-main">
        <div class="solde-label">Solde actuel</div>
        <div class="solde-value" [class.negative]="solde < 0">
          {{ solde | number:'1.0-0' }} FCFA
        </div>
      </div>
      <div class="solde-details">
        <div class="detail entree">
          <span class="detail-label">Entrées</span>
          <span class="detail-value">+{{ totalEntrees | number:'1.0-0' }}</span>
        </div>
        <div class="detail sortie">
          <span class="detail-label">Sorties</span>
          <span class="detail-value">-{{ totalSorties | number:'1.0-0' }}</span>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .solde-card { background: linear-gradient(135deg, #1976d2, #1565c0); color: white; padding: 1.5rem; border-radius: 8px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 1rem; margin-bottom: 1.5rem; }
    .solde-main { display: flex; flex-direction: column; }
    .solde-label { font-size: 0.9rem; opacity: 0.9; margin-bottom: 0.25rem; }
    .solde-value { font-size: 2rem; font-weight: 700; }
    .solde-value.negative { color: #ffcdd2; }
    .solde-details { display: flex; gap: 2rem; }
    .detail { display: flex; flex-direction: column; align-items: flex-end; }
    .detail-label { font-size: 0.8rem; opacity: 0.9; }
    .detail-value { font-size: 1.1rem; font-weight: 600; }
    .detail.entree .detail-value { color: #c8e6c9; }
    .detail.sortie .detail-value { color: #ffcdd2; }
  `]
})
export class SoldeCardComponent {
  @Input() solde: number = 0;
  @Input() totalEntrees: number = 0;
  @Input() totalSorties: number = 0;
}
