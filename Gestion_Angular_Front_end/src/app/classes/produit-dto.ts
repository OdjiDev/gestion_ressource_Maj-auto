import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CategorieDto } from './categorie-dto';
import { MagasinDto } from './magasin-dto';

export class ProduitDto {


  /** Identifiant unique du produit */
  id: number = 0;

  /** Code produit unique */
  codeproduit: string = "";

  /** Nom du produit */
  nom: string = "";

  /** Description détaillée du produit */
  designation: string = "";

  /** Quantité du produit en stock */
  quantite: number = 0;

  // Identifiant de la catégorie du produit (si applicable)
   categorieId?: number;

  // Objet DTO représentant la catégorie du produit (si applicable)
   categorieDto?: CategorieDto;

  // Identifiant du magasin du produit (si applicable)
   magasinId?: number;

  // Objet DTO représentant le magasin du produit (si applicable)
   magasinDto?: MagasinDto;

}

