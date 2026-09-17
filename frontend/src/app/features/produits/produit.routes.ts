import { Routes } from '@angular/router';

export const PRODUIT_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/produit-list/produit-list')
        .then(m => m.ProduitList),
    title: 'Liste des produits'
  },
  {
    path: 'create',
    loadComponent: () =>
      import('./pages/produit-create/produit-create')
        .then(m => m.ProduitCreate),
    title: 'Nouveau produit'
  },
  {
    path: 'update/:id',
    loadComponent: () =>
      import('./pages/produit-update/produit-update')
        .then(m => m.ProduitUpdate),
    title: 'Modifier un produit'
  },
  {
    path: 'detail/:id',
    loadComponent: () =>
      import('./pages/produit-detail/produit-detail')
        .then(m => m.ProduitDetail),
    title: 'Détail du produit'
  }
];
