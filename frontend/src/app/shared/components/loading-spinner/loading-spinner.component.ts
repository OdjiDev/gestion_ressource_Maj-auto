import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-loading-spinner',
  standalone: true,
  template: `
    @if (visible) {
      <div class="spinner-container" [class.overlay]="overlay">
        <div class="spinner"></div>
        @if (message) {
          <p class="spinner-message">{{ message }}</p>
        }
      </div>
    }
  `,
  styles: [`
    .spinner-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: 1rem;
      gap: 0.75rem;
    }
    .spinner-container.overlay {
      position: fixed;
      inset: 0;
      background: rgba(255, 255, 255, 0.75);
      backdrop-filter: blur(2px);
      z-index: 9999;
    }
    .spinner {
      width: 40px;
      height: 40px;
      border: 4px solid #e0e0e0;
      border-top-color: #1976d2;
      border-radius: 50%;
      animation: spin 0.8s linear infinite;
    }
    .spinner-message {
      color: #555;
      font-size: 0.9rem;
      margin: 0;
    }
    @keyframes spin {
      to { transform: rotate(360deg); }
    }
  `]
})
export class LoadingSpinnerComponent {
  @Input() visible = false;
  @Input() overlay = false;
  @Input() message = '';
}
