import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-confirm-dialog',
  standalone: true,
  template: `
    @if (visible) {
      <div class="dialog-overlay" (click)="onCancel()">
        <div class="dialog" (click)="$event.stopPropagation()">
          <div class="dialog-header">
            <h2 class="dialog-title">{{ title }}</h2>
          </div>
          <div class="dialog-body"><p>{{ message }}</p></div>
          <div class="dialog-footer">
            <button class="btn btn-cancel" (click)="onCancel()">{{ cancelText }}</button>
            <button class="btn" [class.btn-danger]="color === 'danger'" [class.btn-primary]="color === 'primary'" (click)="onConfirm()">{{ confirmText }}</button>
          </div>
        </div>
      </div>
    }
  `,
  styles: [`
    .dialog-overlay { position: fixed; inset: 0; background: rgba(0,0,0,0.5); display: flex; align-items: center; justify-content: center; z-index: 10000; }
    .dialog { background: white; border-radius: 8px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); min-width: 400px; max-width: 90vw; }
    .dialog-header { padding: 1.25rem 1.5rem 0.5rem; }
    .dialog-title { margin: 0; font-size: 1.15rem; font-weight: 600; color: #333; }
    .dialog-body { padding: 0.5rem 1.5rem 1.25rem; color: #555; font-size: 0.95rem; }
    .dialog-body p { margin: 0; }
    .dialog-footer { padding: 1rem 1.5rem; display: flex; justify-content: flex-end; gap: 0.75rem; border-top: 1px solid #eee; }
    .btn { padding: 0.6rem 1.25rem; border: none; border-radius: 4px; cursor: pointer; font-size: 0.9rem; font-weight: 500; }
    .btn-cancel { background: #f5f5f5; color: #333; }
    .btn-cancel:hover { background: #e0e0e0; }
    .btn-primary { background: #1976d2; color: white; }
    .btn-primary:hover { background: #1565c0; }
    .btn-danger { background: #d32f2f; color: white; }
    .btn-danger:hover { background: #b71c1c; }
  `]
})
export class ConfirmDialogComponent {
  @Input() visible = false;
  @Input() title = 'Confirmation';
  @Input() message = 'Êtes-vous sûr ?';
  @Input() confirmText = 'Confirmer';
  @Input() cancelText = 'Annuler';
  @Input() color: 'primary' | 'danger' = 'danger';
  @Output() confirmed = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();
  onConfirm(): void { this.confirmed.emit(); }
  onCancel(): void { this.cancelled.emit(); }
}
