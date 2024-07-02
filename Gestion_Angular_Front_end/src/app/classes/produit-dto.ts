
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CategorieDto } from './categorie-dto';
import { MagasinDto } from './magasin-dto';
import { LigneFactureDto } from './lignefacture-dto';

export class ProduitDto {


  /** Identifiant unique du produit */
  id: number = 0;

  /** Code produit unique */
  codeproduit: string = "";

  /** Nom du produit */
  nom: string = "";

  /** Description détaillée du produit */
  designation: string = "";

  prixAchat: number = 0;

   categorieDto:CategorieDto= new CategorieDto();
//  ligneFactureDto:LigneFactureDto= new LigneFactureDto();


}

