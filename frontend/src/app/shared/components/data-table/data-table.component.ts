import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColumnDef } from './column-def';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';
import { EmptyStateComponent } from '../empty-state/empty-state.component';

@Component({
  selector: 'app-data-table',
  standalone: true,
  imports: [CommonModule, LoadingSpinnerComponent, EmptyStateComponent],
  template: `
    <div class="data-table-wrapper">
      @if (loading) {
        <app-loading-spinner [visible]="true" message="Chargement..." />
      } @else if (!data || data.length === 0) {
        <app-empty-state icon="📋" [title]="emptyTitle" [message]="emptyMessage" [actionLabel]="emptyActionLabel" (action)="emptyAction.emit()" />
      } @else {
        <table class="data-table">
          <thead>
            <tr>
              @for (col of columns; track col.key) {
                <th [style.width]="col.width" [style.text-align]="col.align || 'left'" [class.sortable]="col.sortable" (click)="col.sortable ? onSort(col) : null">
                  {{ col.label }}
                  @if (col.sortable && sortKey === col.key) { <span class="sort-icon">{{ sortDir === 'asc' ? '▲' : '▼' }}</span> }
                </th>
              }
              @if (showActions) { <th class="actions-col">Actions</th> }
            </tr>
          </thead>
          <tbody>
            @for (row of data; track trackBy(row)) {
              <tr>
                @for (col of columns; track col.key) {
                  <td [style.text-align]="col.align || 'left'">
                    @switch (col.type) {
                      @case ('badge') { <span class="badge">{{ getCellValue(row, col) }}</span> }
                      @case ('date') { {{ getCellValue(row, col) | date:'dd/MM/yyyy' }} }
                      @case ('currency') { {{ getCellValue(row, col) | number:'1.0-0' }} FCFA }
                      @default { {{ getCellValue(row, col) }} }
                    }
                  </td>
                }
                @if (showActions) {
                  <td class="actions-col">
                    @if (showEdit) { <button class="btn-icon btn-edit" (click)="edit.emit(row)" title="Modifier">✏️</button> }
                    @if (showDelete) { <button class="btn-icon btn-delete" (click)="delete.emit(row)" title="Supprimer">🗑️</button> }
                  </td>
                }
              </tr>
            }
          </tbody>
        </table>
      }
    </div>
  `,
  styles: [`
    .data-table-wrapper { background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 1px 3px rgba(0,0,0,0.08); }
    .data-table { width: 100%; border-collapse: collapse; font-size: 0.9rem; }
    .data-table thead { background: #f8f9fa; border-bottom: 2px solid #e0e0e0; }
    .data-table th { padding: 0.75rem 1rem; font-weight: 600; color: #444; font-size: 0.85rem; text-transform: uppercase; }
    .data-table th.sortable { cursor: pointer; user-select: none; }
    .data-table th.sortable:hover { background: #eef0f2; }
    .sort-icon { margin-left: 0.35rem; font-size: 0.7rem; color: #1976d2; }
    .data-table td { padding: 0.75rem 1rem; border-bottom: 1px solid #f0f0f0; color: #333; }
    .data-table tbody tr:hover { background: #f8f9fa; }
    .badge { display: inline-block; padding: 0.2rem 0.6rem; background: #e3f2fd; color: #1565c0; border-radius: 12px; font-size: 0.8rem; font-weight: 500; }
    .actions-col { width: 120px; text-align: right; }
    .btn-icon { background: none; border: none; cursor: pointer; padding: 0.35rem; font-size: 1rem; border-radius: 4px; }
    .btn-icon:hover { background: #f0f0f0; }
  `]
})
export class DataTableComponent<T = any> {
  @Input() columns: ColumnDef<T>[] = [];
  @Input() data: T[] = [];
  @Input() loading = false;
  @Input() showActions = true;
  @Input() showEdit = true;
  @Input() showDelete = true;
  @Input() emptyTitle = 'Aucune donnée';
  @Input() emptyMessage = '';
  @Input() emptyActionLabel = '';
  @Input() trackByField = 'id';
  @Output() edit = new EventEmitter<T>();
  @Output() delete = new EventEmitter<T>();
  @Output() emptyAction = new EventEmitter<void>();
  @Output() sortChange = new EventEmitter<{ key: string; dir: 'asc' | 'desc' }>();
  sortKey: string | null = null;
  sortDir: 'asc' | 'desc' = 'asc';
  getCellValue(row: T, col: ColumnDef<T>): any {
    const value = (row as any)[col.key];
    if (col.formatter) return col.formatter(value, row);
    return value ?? '';
  }
  onSort(col: ColumnDef<T>): void {
    if (!col.sortable) return;
    if (this.sortKey === col.key) { this.sortDir = this.sortDir === 'asc' ? 'desc' : 'asc'; }
    else { this.sortKey = col.key as string; this.sortDir = 'asc'; }
    this.sortChange.emit({ key: this.sortKey, dir: this.sortDir });
  }
  trackBy(row: T): any { return (row as any)[this.trackByField]; }
}
