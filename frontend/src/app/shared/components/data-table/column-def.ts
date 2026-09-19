export interface ColumnDef<T = any> {
  key: keyof T | string;
  label: string;
  width?: string;
  align?: 'left' | 'center' | 'right';
  sortable?: boolean;
  formatter?: (value: any, row: T) => string;
  type?: 'text' | 'badge' | 'date' | 'currency' | 'number';
}
