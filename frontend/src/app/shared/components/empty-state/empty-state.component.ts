import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-empty-state',
  standalone: true,
  template: `
    <div class="empty-state">
      @if (icon) { <div class="empty-state-icon">{{ icon }}</div> }
      <h3 class="empty-state-title">{{ title }}</h3>
      @if (message) { <p class="empty-state-message">{{ message }}</p> }
      @if (actionLabel) {
        <button class="empty-state-action" (click)="action.emit()">{{ actionLabel }}</button>
      }
    </div>
  `,
  styles: [`
    .empty-state { display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 3rem 1rem; text-align: center; color: #666; }
    .empty-state-icon { font-size: 3rem; margin-bottom: 1rem; opacity: 0.5; }
    .empty-state-title { font-size: 1.25rem; font-weight: 600; margin: 0 0 0.5rem; color: #333; }
    .empty-state-message { font-size: 0.95rem; margin: 0 0 1.5rem; max-width: 400px; }
    .empty-state-action { padding: 0.6rem 1.5rem; background: #1976d2; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 0.95rem; }
    .empty-state-action:hover { background: #1565c0; }
  `]
})
export class EmptyStateComponent {
  @Input() icon = '';
  @Input() title = 'Aucune donnée';
  @Input() message = '';
  @Input() actionLabel = '';
  @Output() action = new EventEmitter<void>();
}
