import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () => import('@features/admin/components/admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent)
  },
  {
    path: 'produits',
    children: [
      { path: '', loadComponent: () => import('@features/admin/interfaces/page-produit/list-produit/list-produit.component').then(m => m.ListProduitComponent) },
      { path: 'create', loadComponent: () => import('@features/admin/interfaces/page-produit/create-produit/create-produit.component').then(m => m.CreateProduitComponent) },
      { path: 'update/:id', loadComponent: () => import('@features/admin/interfaces/page-produit/update-produit/update-produit.component').then(m => m.UpdateProduitComponent) }
    ]
  },
  {
    path: 'categories',
    children: [
      { path: '', loadComponent: () => import('@features/admin/interfaces/categorie/list-categorie/list-categorie.component').then(m => m.ListCategorieComponent) },
      { path: 'create', loadComponent: () => import('@features/admin/interfaces/categorie/create-categorie/create-categorie.component').then(m => m.CreateCategorieComponent) }
    ]
  }
  // ... ajoute les autres routes selon tes besoins
];
