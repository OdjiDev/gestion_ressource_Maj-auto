
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
// import { CreateDepartementComponent } from './interfaces/Departement/1/create-departement.component';
import { CreateCategorieComponent } from './interfaces/categorie/create-categorie/create-categorie.component';
import { ListCategorieComponent } from './interfaces/categorie/list-categorie/list-categorie.component';
import { UpdateDepartementComponent } from './interfaces/Departement/update-departement/update-departement.component';
import { CreateProduitComponent } from './interfaces/page-produit/create-produit/create-produit.component';
import { UpdateProduitComponent } from './interfaces/page-produit/update-produit/update-produit.component';
import { ListProduitComponent } from './interfaces/page-produit/list-produit/list-produit.component';
import { ListDepartementComponent } from './interfaces/Departement/list-departement/list-departement.component';



const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
     children: [
      //Route de departement
      //{ path: 'adddepartement',component: CreateDepartementComponent },
      { path: 'listdepartement',component: ListDepartementComponent },
      { path: 'updatedepartement',component: UpdateDepartementComponent},
     // { path: 'searchcategorie',component: CreateDepartementComponent  },

       //{ path: 'createdep',component: CreateDepartementComponent  },
        //ROUTES DE CATEGORIE
        { path: 'addcategorie',component: CreateCategorieComponent  },
        { path: 'listcategorie',component: ListCategorieComponent  },
        { path: 'updatecategorie',component: UpdateDepartementComponent},
       // { path: 'searchcategorie',component: CreateDepartementComponent  },

       //routes de produit
       { path: 'addproduit',component: CreateProduitComponent  },
       { path: 'listproduit',component: ListProduitComponent  },
       { path: 'updateproduit',component: UpdateProduitComponent},
      // { path: 'searchcategorie',component: CreateDepartementComponent  },
     ],

  },
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule { }
