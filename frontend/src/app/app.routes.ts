import { Routes } from '@angular/router';
import { authGuard } from '@core/auth/auth.guard';
import { adminGuard } from '@core/auth/admin.guard';
import { gestionnaireGuard } from '@core/auth/gestionnaire.guard';
import { comptableGuard } from '@core/auth/comptable.guard';
// import { personnelGuard } from '@core/auth/personnel.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // ==================== PUBLIC ====================
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login')
        .then(m => m.Login),
    title: 'Connexion'
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./features/auth/register/register')
        .then(m => m.Register),
    title: 'Inscription'
  },

  // ==================== ZONE PROTÉGÉE ====================
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./layout/main-layout/main-layout')
        .then(m => m.MainLayout),
    children: [

      // ==================== DASHBOARD (tous) ====================
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./features/dashboard/dashboard.routes')
            .then(m => m.DASHBOARD_ROUTES)
      },

      // ==================== MÉTIER : ADMIN + GESTIONNAIRE ====================
      {
        path: 'produits',
        // canActivate: [gestionnaireGuard], //Commante pour la demos
        loadChildren: () =>
          import('./features/produits/produit.routes')
            .then(m => m.PRODUIT_ROUTES)
      },
      {
        path: 'categories',
        // canActivate: [gestionnaireGuard],  //Commante pour la demos
        loadChildren: () =>
          import('./features/categories/categorie.routes')
            .then(m => m.CATEGORIE_ROUTES)
      },


    ]  }, ]

  //     {
  //       path: 'magasins',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/magasins/magasin.routes')
  //           .then(m => m.MAGASIN_ROUTES)
  //     },
  //     {
  //       path: 'fournisseurs',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/fournisseurs/fournisseur.routes')
  //           .then(m => m.FOURNISSEUR_ROUTES)
  //     },
  //     {
  //       path: 'factures',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/factures/facture.routes')
  //           .then(m => m.FACTURE_ROUTES)
  //     },
  //     {
  //       path: 'factures-reparer',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/factures-reparer/facture-reparer.routes')
  //           .then(m => m.FACTURE_REPARER_ROUTES)
  //     },
  //     {
  //       path: 'societes',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/societes/societe.routes')
  //           .then(m => m.SOCIETE_ROUTES)
  //     },
  //     {
  //       path: 'contrats',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/contrats/contrat.routes')
  //           .then(m => m.CONTRAT_ROUTES)
  //     },
  //     {
  //       path: 'demandes',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/demandes/demande.routes')
  //           .then(m => m.DEMANDE_ROUTES)
  //     },
  //     {
  //       path: 'reparations',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/reparations/reparer.routes')
  //           .then(m => m.REPARER_ROUTES)
  //     },
  //     {
  //       path: 'signalements',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/signalements/signaler.routes')
  //           .then(m => m.SIGNALER_ROUTES)
  //     },
  //     {
  //       path: 'affectations',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/affectations/affectation.routes')
  //           .then(m => m.AFFECTATION_ROUTES)
  //     },
  //     {
  //       path: 'avaries',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/avaries/avarie.routes')
  //           .then(m => m.AVARIE_ROUTES)
  //     },
  //     {
  //       path: 'bureaux',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/bureaux/bureau.routes')
  //           .then(m => m.BUREAU_ROUTES)
  //     },
  //     {
  //       path: 'departements',
  //       canActivate: [gestionnaireGuard],
  //       loadChildren: () =>
  //         import('./features/departements/departement.routes')
  //           .then(m => m.DEPARTEMENT_ROUTES)
  //     },

  //     // ==================== PERSONNELS : ADMIN + PERSONNEL ====================
  //     {
  //       path: 'personels',
  //       canActivate: [personnelGuard],
  //       loadChildren: () =>
  //         import('./features/personels/personel.routes')
  //           .then(m => m.PERSONEL_ROUTES)
  //     },

  //     // ==================== COMPTABILITÉ : ADMIN + COMPTABLE ====================
  //     {
  //       path: 'comptabilite',
  //       canActivate: [comptableGuard],
  //       loadChildren: () =>
  //         import('./features/comptabilite/comptabilite.routes')
  //           .then(m => m.COMPTABILITE_ROUTES)
  //     },

  //     // ==================== ADMIN UNIQUEMENT ====================
  //     {
  //       path: 'admin',
  //       canActivate: [adminGuard],
  //       loadChildren: () =>
  //         import('./features/admin/admin.routes')
  //           .then(m => m.ADMIN_ROUTES)
  //     }
  //   ]
  // },

 // { path: '**', redirectTo: 'login' }
//];
