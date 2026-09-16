import { Routes } from '@angular/router';

export const COMPTABLE_ROUTES: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () => import('@features/comptable/components/comptable-dashboard/comptable-dashboard.component').then(m => m.ComptableDashboardComponent)
  }
];
