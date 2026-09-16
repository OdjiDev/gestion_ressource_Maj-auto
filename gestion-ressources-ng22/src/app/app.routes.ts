import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { adminGuard } from './core/guards/admin.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent)
  },

  {
    path: 'admin',
    // canActivate: [authGuard, adminGuard],
    loadChildren: () => import('./features/admin/admin.routes').then(m => m.ADMIN_ROUTES)
  },

   {
    path: 'admin',
    // canActivate: [authGuard, adminGuard],
    loadChildren: () =>
      import('./features/admin/admin.routes')
        .then(m => m.ADMIN_ROUTES)
  },
  {
    path: 'comptable',
    canActivate: [authGuard],
    loadChildren: () => import('./features/comptable/comptable.routes').then(m => m.COMPTABLE_ROUTES)
  },
  {
    path: 'personnel',
    canActivate: [authGuard],
    loadChildren: () => import('./features/personnel/personnel.routes').then(m => m.PERSONNEL_ROUTES)
  },

  { path: '**', redirectTo: 'login' }
];
