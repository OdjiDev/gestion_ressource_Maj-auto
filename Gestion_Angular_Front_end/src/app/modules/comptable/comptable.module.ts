import { Router, RouterModule } from '@angular/router';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComptableRoutingModule } from './comptable-routing.module';
import { ComptableDashboardComponent } from './components/comptable-dashboard/comptable-dashboard.component';
import { CreateProcesVerbalReceptionComponent } from './interfaces/create-proces-verbal-reception/create-proces-verbal-reception.component';
import { ListProcesVerbalReceptionComponent } from './interfaces/list-proces-verbal-reception/list-proces-verbal-reception.component';
import { CreateOrdreMouvementComponent } from './interfaces/create-ordre-mouvement/create-ordre-mouvement.component';
import { ListOrdreMouvementComponent } from './interfaces/list-ordre-mouvement/list-ordre-mouvement.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';





@NgModule({
  declarations: [
    ComptableDashboardComponent,
    SideBarComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,

    CreateProcesVerbalReceptionComponent,
    ListProcesVerbalReceptionComponent,
    CreateOrdreMouvementComponent,
    ListOrdreMouvementComponent,


],


imports: [
  CommonModule,
  ComptableRoutingModule,
  FormsModule,
  ReactiveFormsModule
]
})
export class ComptableModule { }

