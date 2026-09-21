import { Routes } from '@angular/router';

export const TRESORERIE_ROUTES: Routes = [
  // ==================== COMPTES ====================
  {
    path: 'comptes',
    loadComponent: () => import('./compte/pages/compte-list/compte-list').then(m => m.CompteList),
    title: 'Comptes de trésorerie'
  },
  {
    path: 'comptes/create',
    loadComponent: () => import('./compte/pages/compte-create/compte-create').then(m => m.CompteCreate),
    title: 'Nouveau compte'
  },
  {
    path: 'comptes/update/:id',
    loadComponent: () => import('./compte/pages/compte-update/compte-update').then(m => m.CompteUpdate),
    title: 'Modifier un compte'
  },
  {
    path: 'comptes/detail/:id',
    loadComponent: () => import('./compte/pages/compte-detail/compte-detail').then(m => m.CompteDetail),
    title: 'Détail du compte'
  },

  // ==================== MOUVEMENTS ====================
  {
    path: 'mouvements',
    loadComponent: () => import('./mouvement/pages/mouvement-list/mouvement-list').then(m => m.MouvementList),
    title: 'Mouvements de trésorerie'
  },
  {
    path: 'mouvements/create',
    loadComponent: () => import('./mouvement/pages/mouvement-create/mouvement-create').then(m => m.MouvementCreate),
    title: 'Nouveau mouvement'
  },
  {
    path: 'mouvements/update/:id',
    loadComponent: () => import('./mouvement/pages/mouvement-update/mouvement-update').then(m => m.MouvementUpdate),
    title: 'Modifier un mouvement'
  },
  {
    path: 'mouvements/detail/:id',
    loadComponent: () => import('./mouvement/pages/mouvement-detail/mouvement-detail').then(m => m.MouvementDetail),
    title: 'Détail du mouvement'
  },

  // ==================== DEFAULT ====================
  { path: '', redirectTo: 'comptes', pathMatch: 'full' }
];
