import { Routes } from '@angular/router';
import { authGuard } from '@core/auth/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // ==================== PUBLIC ====================
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login').then(m => m.Login),
    title: 'Connexion'
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register/register').then(m => m.Register),
    title: 'Inscription'
  },

  // ==================== ZONE PROTÉGÉE ====================
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./layout/main-layout/main-layout').then(m => m.MainLayout),
    children: [
      // Dashboard
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./features/dashboard/dashboard.routes').then(m => m.DASHBOARD_ROUTES)
      },

      // Produits
      {
        path: 'produits',
        loadChildren: () => import('./features/produits/produit.routes').then(m => m.PRODUIT_ROUTES)
      },

      // Catégories
      {
        path: 'categories',
        loadChildren: () =>
          import('./features/categories/categorie.routes').then(m => m.CATEGORIE_ROUTES)
      },

      // ==================== TRÉSORERIE ====================
      {
        path: 'tresorerie',
        loadChildren: () =>
          import('./features/tresorerie/tresorerie.routes').then(m => m.TRESORERIE_ROUTES)
      }
    ]
  },

  { path: '**', redirectTo: 'login' }
];
