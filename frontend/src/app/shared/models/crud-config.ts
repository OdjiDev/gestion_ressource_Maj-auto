export type FieldType = 'text' | 'number' | 'email' | 'password' | 'select' | 'textarea' | 'checkbox' | 'date';
export interface SelectOption { value: any; label: string; }
export interface CrudField {
  name: string; label: string; type: FieldType;
  placeholder?: string; required?: boolean; defaultValue?: any;
  options?: SelectOption[]; minLength?: number; maxLength?: number;
  colSpan?: 1 | 2; disabled?: boolean;
}
export interface CrudFormConfig {
  fields: CrudField[];
  submitLabel?: string; cancelLabel?: string; showCancel?: boolean;
}
