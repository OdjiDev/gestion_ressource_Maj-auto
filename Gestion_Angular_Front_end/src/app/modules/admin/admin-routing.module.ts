import { DetailDepartementComponent } from './interfaces/departement/detail-departement/detail-departement.component';

import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { CreateCategorieComponent } from './interfaces/categorie/create-categorie/create-categorie.component';
import { ListCategorieComponent } from './interfaces/categorie/list-categorie/list-categorie.component';
import { CreateProduitComponent } from './interfaces/page-produit/create-produit/create-produit.component';
import { UpdateProduitComponent } from './interfaces/page-produit/update-produit/update-produit.component';
import { ListProduitComponent } from './interfaces/page-produit/list-produit/list-produit.component';
import { ListBureauComponent } from './interfaces/Bureau/list-bureau/list-bureau.component';
import { CreateBureauComponent } from './interfaces/Bureau/create-bureau/create-bureau.component';
import { UpdateBureauComponent } from './interfaces/Bureau/update-bureau/update-bureau.component';
import { CreateDepartementComponent } from './interfaces/departement/create-departement/create-departement.component';
import { ListDepartementComponent } from './interfaces/departement/list-departement/list-departement.component';
import { UpdateDepartementComponent } from './interfaces/departement/update-departement/update-departement.component';
import { UpdateDemandeComponent } from './interfaces/demande/update-demande/update-demande.component';
import { ListDemandeComponent } from './interfaces/demande/list-demande/list-demande.component';
import { CreateDemandeComponent } from './interfaces/demande/create-demande/create-demande.component';
import { CreateFactureComponent } from './interfaces/facture/create-facture/create-facture.component';
import { ListFactureComponent } from './interfaces/facture/list-facture/list-facture.component';
import { UpdateFactureComponent } from './interfaces/facture/update-facture/update-facture.component';
import { ListFournisseurComponent } from './interfaces/fournisseur/list-fournisseur/list-fournisseur.component';
import { UpdateFournisseurComponent } from './interfaces/fournisseur/update-fournisseur/update-fournisseur.component';
import { CreateFournisseurComponent } from './interfaces/fournisseur/create-fournisseur/create-fournisseur.component';
import { CreateAvarieComponent } from './interfaces/avarie/create-avarie/create-avarie.component';
import { ListAvarieComponent } from './interfaces/avarie/list-avarie/list-avarie.component';
import { UpdateAvarieComponent } from './interfaces/avarie/update-avarie/update-avarie.component';
import { UpdateSocieteComponent } from './interfaces/societe/update-societe/update-societe.component';
import { ListSocieteComponent } from './interfaces/societe/list-societe/list-societe.component';
import { CreateSocieteComponent } from './interfaces/societe/create-societe/create-societe.component';
import { CreateContratComponent } from './interfaces/contrat/create-contrat/create-contrat.component';
import { ListContratComponent } from './interfaces/contrat/list-contrat/list-contrat.component';
import { UpdateContratComponent } from './interfaces/contrat/update-contrat/update-contrat.component';
import { UpdatePersonelComponent } from './interfaces/personel/update-personel/update-personel.component';
import { ListPersonelComponent } from './interfaces/personel/list-personel/list-personel.component';

import { CreatePersonelComponent } from './interfaces/personel/create-personel/create-personel.component';
import { CreateAffectationComponent } from './interfaces/affectation/create-affectation/create-affectation.component';
import { ListAffectationComponent } from './interfaces/affectation/list-affectation/list-affectation.component';
import { UpdateAffectationComponent } from './interfaces/affectation/update-affectation/update-affectation.component';


const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
     children: [


       //{ path: 'createdep',component: CreateDepartementComponent  },
        //ROUTES DE CATEGORIE
        { path: 'addcategorie',component: CreateCategorieComponent  },
        { path: 'listcategorie',component: ListCategorieComponent  },
        //{ path: 'updatecategorie',component: UpdateCatgorieComponent },
       // { path: 'searchcategorie',component: CreateDepartementComponent  },

       //routes de produit
       { path: 'addproduit',component: CreateProduitComponent  },
       { path: 'listproduit',component: ListProduitComponent  },
       { path: 'updateproduit',component: UpdateProduitComponent},
      // { path: 'searchcategorie',component: CreateDepartementComponent  },


       //routes de bureau
       { path: 'add-bureau',component: CreateBureauComponent },
       { path: 'list-bureau',component: ListBureauComponent  },
       { path: 'update-bureau/:id',component: UpdateBureauComponent},
       { path: 'detail-bureau/:id',component: DetailDepartementComponent  },

      //routes de departement
      { path: 'adddepartement',component: CreateDepartementComponent },
      { path: 'listdepartement',component: ListDepartementComponent  },
      { path: 'updatedepartement/:id',component: UpdateDepartementComponent},
       {path:'detailsdepartement',component:DetailDepartementComponent},
     // { path: 'searchcategorie',component: CreateDepartementComponent  },

    //routes de departement
      { path: 'adddemande',component: CreateDemandeComponent },
      { path: 'listdemande',component: ListDemandeComponent  },
      { path: 'updatedemande',component: UpdateDemandeComponent},
     // { path: 'searchdemande',component: CreateDemandeComponent  },
      //routes de facture
      { path: 'addfacture',component: CreateFactureComponent },
      { path: 'listfacture',component: ListFactureComponent  },
      { path: 'updatefacture',component: UpdateFactureComponent},
     // { path: 'searchfacture',component: CreateFactureComponent  },
     //routes de fournisseur
     { path: 'addfournisseur',component: CreateFournisseurComponent },
     { path: 'listfournisseur',component: ListFournisseurComponent  },
     { path: 'updatefournisseur',component: UpdateFournisseurComponent},
    // { path: 'searchfournisseur',component: CreateFournisseurComponent  },

     //routes des avaries
     { path: 'addavarie',component: CreateAvarieComponent },
     { path: 'listavarie',component: ListAvarieComponent  },
     { path: 'updateavarie',component: UpdateAvarieComponent},
    // { path: 'searchavarie',component: CreateAvarieComponent  },
  //routes des avaries
  { path: 'addsociete',component: CreateSocieteComponent },
  { path: 'listsociete',component: ListSocieteComponent  },
  { path: 'updatesociete',component: UpdateSocieteComponent},
 // { path: 'searchsociete',component: CreateSocieteComponent  },
   //routes des avaries
   { path: 'addcontrat',component: CreateContratComponent },
   { path: 'listcontrat',component: ListContratComponent  },
   { path: 'updatecontrat',component: UpdateContratComponent},
  // { path: 'searchcontrat',component: CreateContratComponent  },
  //routes des personels
  { path: 'addpersonel',component: CreatePersonelComponent },
  { path: 'listpersonel',component: ListPersonelComponent  },
  { path: 'updatepersonel',component: UpdatePersonelComponent},
 // { path: 'searchpersonel',component: CreatePersonelComponent  },


  //routes des affectation
  { path: 'add-affectation',component: CreateAffectationComponent },
  { path: 'list-affectation',component: ListAffectationComponent  },
  { path: 'update-affectation/:id',component: UpdateAffectationComponent},
 // { path: 'searchaffectation',component: CreateAffectationComponent  },


    ],

  },
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule { }
