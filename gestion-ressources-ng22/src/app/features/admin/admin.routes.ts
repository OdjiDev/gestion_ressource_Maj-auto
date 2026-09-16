import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },

  {
    path: 'dashboard',
    loadComponent: () =>
      // import('./components/admin-dashboard/admin-dashboard.component')
      import('./interfaces/page-produit/list-produit/list-produit.component')
        .then(m => m.ListProduitComponent)
        //  .then(m => m.AdminDashboardComponent)
  },

  {
    path: 'produits',
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./interfaces/page-produit/list-produit/list-produit.component')
            .then(m => m.ListProduitComponent)
      },
      {
        path: 'create',
        loadComponent: () =>
          import('./interfaces/page-produit/create-produit/create-produit.component')
            .then(m => m.CreateProduitComponent)
      },
      {
        path: 'update/:id',
        loadComponent: () =>
          import('./interfaces/page-produit/update-produit/update-produit.component')
            .then(m => m.UpdateProduitComponent)
      }
    ]
  },

  {
    path: 'categories',
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./interfaces/categorie/list-categorie/list-categorie.component')
            .then(m => m.ListCategorieComponent)
      },
      {
        path: 'create',
        loadComponent: () =>
          import('./interfaces/categorie/create-categorie/create-categorie.component')
            .then(m => m.CreateCategorieComponent)
      }
    ]
  }

  // ⚠️ Ajoute les autres routes (magasin, fournisseur, facture, etc.) au fur et à mesure
];
