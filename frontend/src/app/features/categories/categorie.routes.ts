import { Routes } from '@angular/router';

export const CATEGORIE_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/categorie-list/categorie-list').then(m => m.CategorieList),
    title: 'Liste des catégories'
  },
  {
    path: 'create',
    loadComponent: () =>
      import('./pages/categorie-create/categorie-create').then(m => m.CategorieCreate),
    title: 'Nouvelle catégorie'
  },
  {
    path: 'update/:id',
    loadComponent: () =>
      import('./pages/categorie-update/categorie-update').then(m => m.CategorieUpdate),
    title: 'Modifier une catégorie'
  }
];
