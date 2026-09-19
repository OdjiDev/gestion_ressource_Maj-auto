import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl } from '@angular/forms';
import { CrudFormConfig } from '../../models/crud-config';
import { FieldErrorDisplayComponent } from '../field-error-display/field-error-display.component';

@Component({
  selector: 'app-crud-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FieldErrorDisplayComponent],
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()" class="crud-form">
      <div class="form-grid">
        @for (field of config.fields; track field.name) {
          <div class="form-field" [class.col-span-2]="field.colSpan === 2">
            <label [for]="field.name" class="form-label">{{ field.label }} @if (field.required) { <span class="required">*</span> }</label>
            @switch (field.type) {
              @case ('textarea') { <textarea [id]="field.name" [formControlName]="field.name" [placeholder]="field.placeholder || ''" rows="4" class="form-control"></textarea> }
              @case ('select') {
                <select [id]="field.name" [formControlName]="field.name" class="form-control">
                  <option value="">-- Sélectionner --</option>
                  @for (opt of field.options || []; track opt.value) { <option [value]="opt.value">{{ opt.label }}</option> }
                </select>
              }
              @case ('checkbox') { <label class="checkbox-label"><input type="checkbox" [formControlName]="field.name" /><span>{{ field.placeholder || field.label }}</span></label> }
              @default { <input [id]="field.name" [type]="field.type" [formControlName]="field.name" [placeholder]="field.placeholder || ''" class="form-control" /> }
            }
            @if (field.type !== 'checkbox') { <app-field-error-display [control]="getControl(field.name)" [label]="field.label" /> }
          </div>
        }
      </div>
      <div class="form-actions">
        @if (config.showCancel !== false) { <button type="button" class="btn btn-cancel" (click)="cancel.emit()">{{ config.cancelLabel || 'Annuler' }}</button> }
        <button type="submit" class="btn btn-primary" [disabled]="form.invalid || submitting">{{ config.submitLabel || 'Enregistrer' }}</button>
      </div>
    </form>
  `,
  styles: [`
    .crud-form { background: white; padding: 1.5rem; border-radius: 8px; }
    .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1.25rem 1.5rem; margin-bottom: 1.5rem; }
    .form-field { display: flex; flex-direction: column; }
    .form-field.col-span-2 { grid-column: span 2; }
    .form-label { font-size: 0.9rem; font-weight: 500; color: #333; margin-bottom: 0.4rem; }
    .required { color: #d32f2f; margin-left: 0.25rem; }
    .form-control { padding: 0.6rem 0.85rem; border: 1px solid #ccc; border-radius: 4px; font-size: 0.95rem; width: 100%; box-sizing: border-box; }
    .form-control:focus { outline: none; border-color: #1976d2; box-shadow: 0 0 0 2px rgba(25,118,210,0.15); }
    textarea.form-control { resize: vertical; min-height: 80px; }
    .checkbox-label { display: flex; align-items: center; gap: 0.5rem; cursor: pointer; font-size: 0.95rem; padding-top: 0.4rem; }
    .form-actions { display: flex; justify-content: flex-end; gap: 0.75rem; padding-top: 1rem; border-top: 1px solid #eee; }
    .btn { padding: 0.6rem 1.5rem; border: none; border-radius: 4px; cursor: pointer; font-size: 0.95rem; font-weight: 500; }
    .btn-primary { background: #1976d2; color: white; }
    .btn-primary:hover:not(:disabled) { background: #1565c0; }
    .btn-primary:disabled { background: #90a4ae; cursor: not-allowed; }
    .btn-cancel { background: #f5f5f5; color: #333; }
    .btn-cancel:hover { background: #e0e0e0; }
    @media (max-width: 600px) { .form-grid { grid-template-columns: 1fr; } .form-field.col-span-2 { grid-column: span 1; } }
  `]
})
export class CrudFormComponent implements OnInit {
  @Input() config!: CrudFormConfig;
  @Input() initialData: any = null;
  @Input() submitting = false;
  @Output() save = new EventEmitter<any>();
  @Output() cancel = new EventEmitter<void>();
  form!: FormGroup;
  constructor(private fb: FormBuilder) {}
  ngOnInit(): void { this.buildForm(); }
  private buildForm(): void {
    const group: any = {};
    for (const field of this.config.fields) {
      const validators = [];
      if (field.required) validators.push(Validators.required);
      if (field.type === 'email') validators.push(Validators.email);
      if (field.minLength) validators.push(Validators.minLength(field.minLength));
      if (field.maxLength) validators.push(Validators.maxLength(field.maxLength));
      const initialValue = this.initialData?.[field.name] ?? field.defaultValue ?? this.getDefaultValue(field.type);
      group[field.name] = [{ value: initialValue, disabled: field.disabled || false }, validators];
    }
    this.form = this.fb.group(group);
  }
  private getDefaultValue(type: string): any {
    switch (type) { case 'number': return 0; case 'checkbox': return false; default: return ''; }
  }
  getControl(name: string): AbstractControl | null { return this.form.get(name); }
  onSubmit(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.save.emit(this.form.getRawValue());
  }
}
