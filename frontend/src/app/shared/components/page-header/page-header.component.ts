import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-page-header',
  standalone: true,
  template: `
    <header class="page-header">
      <div class="page-header-left">
        <h1 class="page-header-title">{{ title }}</h1>
        @if (subtitle) { <p class="page-header-subtitle">{{ subtitle }}</p> }
      </div>
      @if (actionLabel) {
        <button class="page-header-action" (click)="action.emit()">
          @if (actionIcon) { <span class="action-icon">{{ actionIcon }}</span> }
          {{ actionLabel }}
        </button>
      }
    </header>
  `,
  styles: [`
    .page-header { display: flex; justify-content: space-between; align-items: center; padding: 1rem 0; margin-bottom: 1.5rem; border-bottom: 1px solid #e0e0e0; gap: 1rem; flex-wrap: wrap; }
    .page-header-title { margin: 0; font-size: 1.5rem; font-weight: 600; color: #333; }
    .page-header-subtitle { margin: 0.25rem 0 0; font-size: 0.9rem; color: #777; }
    .page-header-action { display: inline-flex; align-items: center; gap: 0.5rem; padding: 0.6rem 1.25rem; background: #1976d2; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 0.95rem; }
    .page-header-action:hover { background: #1565c0; }
    .action-icon { font-size: 1.1rem; }
  `]
})
export class PageHeaderComponent {
  @Input() title = '';
  @Input() subtitle = '';
  @Input() actionLabel = '';
  @Input() actionIcon = '';
  @Output() action = new EventEmitter<void>();
}
