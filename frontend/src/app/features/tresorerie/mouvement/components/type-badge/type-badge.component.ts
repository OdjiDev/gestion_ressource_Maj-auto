import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TypeMouvement } from '../../../shared/enums';

@Component({
  selector: 'app-type-badge',
  standalone: true,
  imports: [CommonModule],
  template: `
    <span class="type-badge" [class.entree]="type === 'ENTREE'" [class.sortie]="type === 'SORTIE'">
      @if (type === 'ENTREE') { ↑ Entrée } @else { ↓ Sortie }
    </span>
  `,
  styles: [`
    .type-badge { padding: 0.25rem 0.75rem; border-radius: 12px; font-size: 0.8rem; font-weight: 600; display: inline-block; }
    .type-badge.entree { background: #e8f5e9; color: #2e7d32; }
    .type-badge.sortie { background: #ffebee; color: #c62828; }
  `]
})
export class TypeBadgeComponent {
  @Input() type: TypeMouvement = 'ENTREE';
}
