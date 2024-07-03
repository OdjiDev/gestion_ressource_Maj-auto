import { CreateProduitComponent } from './interfaces/page-produit/create-produit/create-produit.component';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { AdminRoutingModule } from './admin-routing.module';
import { CategorieDetailsComponent } from './interfaces/categorie/categorie-details/categorie-details.component';
import { ListCategorieComponent } from './interfaces/categorie/list-categorie/list-categorie.component';
import { CreateCategorieComponent } from './interfaces/categorie/create-categorie/create-categorie.component';
import { ListProduitComponent } from './interfaces/page-produit/list-produit/list-produit.component';
import { CreateBureauComponent } from './interfaces/Bureau/create-bureau/create-bureau.component';
import { ListBureauComponent } from './interfaces/Bureau/list-bureau/list-bureau.component';
import { UpdateBureauComponent } from './interfaces/Bureau/update-bureau/update-bureau.component';
<<<<<<< HEAD
// import { CreateDepartementComponent } from './interfaces/departement/create-departement/create-departement.component';
// import { ListDepartementComponent } from './interfaces/departement/list-departement/list-departement.component';
// import { UpdateDepartementComponent } from './interfaces/departement/update-departement/update-departement.component';
=======
>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547
import { CreateDemandeComponent } from './interfaces/demande/create-demande/create-demande.component';
import { ListDemandeComponent } from './interfaces/demande/list-demande/list-demande.component';
import { UpdateDemandeComponent } from './interfaces/demande/update-demande/update-demande.component';
import { ListFournisseurComponent } from './interfaces/fournisseur/list-fournisseur/list-fournisseur.component';
import { UpdateFournisseurComponent } from './interfaces/fournisseur/update-fournisseur/update-fournisseur.component';
import { CreateFournisseurComponent } from './interfaces/fournisseur/create-fournisseur/create-fournisseur.component';
import { DetailFournisseurComponent } from './interfaces/fournisseur/detail-fournisseur/detail-fournisseur.component';
import { SearchFournisseurComponent } from './interfaces/fournisseur/search-fournisseur/search-fournisseur.component';
import { CreateAvarieComponent } from './interfaces/avarie/create-avarie/create-avarie.component';
import { UpdateAvarieComponent } from './interfaces/avarie/update-avarie/update-avarie.component';
import { ListAvarieComponent } from './interfaces/avarie/list-avarie/list-avarie.component';
import { DetailAvarieComponent } from './interfaces/avarie/detail-avarie/detail-avarie.component';
import { SearchAvarieComponent } from './interfaces/avarie/search-avarie/search-avarie.component';
import { CreateSocieteComponent } from './interfaces/societe/create-societe/create-societe.component';
import { UpdateSocieteComponent } from './interfaces/societe/update-societe/update-societe.component';
import { DetailSocieteComponent } from './interfaces/societe/detail-societe/detail-societe.component';
import { SearchSocieteComponent } from './interfaces/societe/search-societe/search-societe.component';
import { ListSocieteComponent } from './interfaces/societe/list-societe/list-societe.component';
import { CreateContratComponent } from './interfaces/contrat/create-contrat/create-contrat.component';
import { DetailContratComponent } from './interfaces/contrat/detail-contrat/detail-contrat.component';
import { UpdateContratComponent } from './interfaces/contrat/update-contrat/update-contrat.component';
import { ListContratComponent } from './interfaces/contrat/list-contrat/list-contrat.component';
import { CreatePersonelComponent } from './interfaces/personel/create-personel/create-personel.component';
import { UpdatePersonelComponent } from './interfaces/personel/update-personel/update-personel.component';
import { ListPersonelComponent } from './interfaces/personel/list-personel/list-personel.component';
import { DetailPersonelComponent } from './interfaces/personel/detail-personel/detail-personel.component';
import { HeaderfactureComponent } from './interfaces/facture/headerfacture/headerfacture.component';
<<<<<<< HEAD


=======
>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547
import { CreateAffectationComponent } from './interfaces/affectation/create-affectation/create-affectation.component';
import { ListAffectationComponent } from './interfaces/affectation/list-affectation/list-affectation.component';
import { DetailAffectationComponent } from './interfaces/affectation/detail-affectation/detail-affectation.component';
import { UpdateAffectationComponent } from './interfaces/affectation/update-affectation/update-affectation.component';
import { FieldErrorDisplayComponent } from 'src/app/field-error-display/field-error-display.component';
<<<<<<< HEAD
import { CreateDepartementComponent } from './interfaces/Departement/create-departement/create-departement.component';
import { ListDepartementComponent } from './interfaces/Departement/list-departement/list-departement.component';
import { UpdateDepartementComponent } from './interfaces/Departement/update-departement/update-departement.component';
import { DetailDepartementComponent } from './interfaces/Departement/detail-departement/detail-departement.component';
=======
import { DetailDepartementComponent } from './interfaces/Departement/detail-departement/detail-departement.component';
import { DetailBureauComponent } from './interfaces/Bureau/detail-bureau/detail-bureau.component';
import { CreateDepartementComponent } from './interfaces/Departement/create-departement/create-departement.component';
import { ListDepartementComponent } from './interfaces/Departement/list-departement/list-departement.component';
import { UpdateDepartementComponent } from './interfaces/Departement/update-departement/update-departement.component';
import { CreateFactureComponent } from './interfaces/facture/create-facture/create-facture.component';
>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547

@NgModule({
  declarations: [
    AdminDashboardComponent,
    HeaderComponent,
    SideBarComponent,
    FooterComponent,
    HomeComponent,
    CreateCategorieComponent,
    CreateCategorieComponent,
    CategorieDetailsComponent,
    ListCategorieComponent,
    ListProduitComponent,

    CreateBureauComponent,
    ListBureauComponent,
    UpdateBureauComponent,

    CreateDepartementComponent,
    ListDepartementComponent,
    UpdateDepartementComponent,
    CreateDemandeComponent,
    ListDemandeComponent,
    UpdateDemandeComponent,
    CreateProduitComponent,
    ListBureauComponent,
    ListFournisseurComponent,
    UpdateFournisseurComponent,
    CreateFournisseurComponent,
    DetailFournisseurComponent,
    SearchFournisseurComponent,
    CreateFactureComponent,
    ListFactureComponent,
    CreateAvarieComponent,
    UpdateAvarieComponent,
    ListAvarieComponent,
    DetailAvarieComponent,
    SearchAvarieComponent,
    CreateSocieteComponent,
    UpdateSocieteComponent,
    DetailSocieteComponent,
    SearchSocieteComponent,
    ListSocieteComponent,
    CreateContratComponent,
    DetailContratComponent,
    UpdateContratComponent,
    ListContratComponent,
    CreatePersonelComponent,
    UpdatePersonelComponent,
    ListPersonelComponent,
    DetailPersonelComponent,
    HeaderfactureComponent,
    FieldErrorDisplayComponent,
    CreateAffectationComponent,
    ListAffectationComponent,
    DetailAffectationComponent,
    UpdateAffectationComponent,
    FieldErrorDisplayComponent,
    DetailDepartementComponent,
   
  ],

  imports: [CommonModule, AdminRoutingModule, FormsModule, ReactiveFormsModule],
})
export class AdminModule {}
