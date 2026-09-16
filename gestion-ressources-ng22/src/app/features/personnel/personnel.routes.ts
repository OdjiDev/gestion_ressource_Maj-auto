import { Routes } from '@angular/router';

export const PERSONNEL_ROUTES: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () => import('@features/personnel/components/personel-dashboard/personel-dashboard.component').then(m => m.PersonelDashboardComponent)
  }
];
