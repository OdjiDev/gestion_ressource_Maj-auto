import { Routes } from '@angular/router';

export const TRESORERIE_ROUTES: Routes = [
  {
    path: 'comptes',
    loadComponent: () =>
      import('./compte/pages/compte-list/compte-list').then(m => m.CompteList),
    title: 'Comptes de trésorerie'
  },
  {
    path: 'comptes/create',
    loadComponent: () =>
      import('./compte/pages/compte-create/compte-create').then(m => m.CompteCreate),
    title: 'Nouveau compte'
  },
  {
    path: 'comptes/update/:id',
    loadComponent: () =>
      import('./compte/pages/compte-update/compte-update').then(m => m.CompteUpdate),
    title: 'Modifier un compte'
  },
  {
    path: 'comptes/detail/:id',
    loadComponent: () =>
      import('./compte/pages/compte-detail/compte-detail').then(m => m.CompteDetail),
    title: 'Détail du compte'
  },
  { path: '', redirectTo: 'comptes', pathMatch: 'full' }
];
