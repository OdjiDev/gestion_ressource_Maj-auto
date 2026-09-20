import { Component, Input } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-field-error-display',
  standalone: true,
  template: `
    @if (control && control.invalid && (control.dirty || control.touched)) {
      <div class="field-error">{{ getErrorMessage() }}</div>
    }
  `,
  styles: [
    `
      .field-error {
        color: #d32f2f;
        font-size: 0.8rem;
        margin-top: 0.25rem;
      }
      .field-error::before {
        content: '⚠ ';
        font-size: 0.9rem;
      }
    `
  ]
})
export class FieldErrorDisplayComponent {
  @Input() control: AbstractControl | null = null;
  @Input() label = 'Ce champ';
  @Input() customMessages: Record<string, string> = {};

  getErrorMessage(): string {
    if (!this.control || !this.control.errors) return '';
    const errors: ValidationErrors = this.control.errors;
    for (const key of Object.keys(errors)) {
      if (this.customMessages[key]) return this.customMessages[key];
      switch (key) {
        case 'required':
          return `${this.label} est obligatoire`;
        case 'email':
          return 'Adresse email invalide';
        case 'minlength':
          return `Minimum ${errors[key].requiredLength} caractères`;
        case 'maxlength':
          return `Maximum ${errors[key].requiredLength} caractères`;
        case 'min':
          return `Valeur minimale : ${errors[key].min}`;
        case 'max':
          return `Valeur maximale : ${errors[key].max}`;
        case 'pattern':
          return 'Format invalide';
        default:
          return 'Champ invalide';
      }
    }
    return '';
  }
}
