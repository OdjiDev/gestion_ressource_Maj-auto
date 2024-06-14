import { CreateProduitComponent } from './interfaces/page-produit/create-produit/create-produit.component';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { Router, RouterModule } from '@angular/router';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { AdminRoutingModule } from './admin-routing.module';
import { CreateDepartementComponent } from './interfaces/Departement/1/create-departement.component';
import { CategorieDetailsComponent } from './interfaces/categorie/categorie-details/categorie-details.component';
import { ListCategorieComponent } from './interfaces/categorie/list-categorie/list-categorie.component';
import { CreateCategorieComponent } from './interfaces/categorie/create-categorie/create-categorie.component';
import { ListProduitComponent } from './interfaces/page-produit/list-produit/list-produit.component';
import { ListDepartementComponent } from './interfaces/Departement/list-departement/list-departement.component';
import { UpdateDepartementComponent } from './interfaces/Departement/update-departement/update-departement.component';

@NgModule({
  declarations: [ AdminDashboardComponent,HeaderComponent,SideBarComponent,
    FooterComponent, HomeComponent
    ,CreateCategorieComponent,ListDepartementComponent,UpdateDepartementComponent,
    CreateCategorieComponent,CategorieDetailsComponent,ListCategorieComponent,
     ListProduitComponent,],



  imports: [CommonModule, AdminRoutingModule, FormsModule,
    ReactiveFormsModule, ],
})
export class AdminModule { }
