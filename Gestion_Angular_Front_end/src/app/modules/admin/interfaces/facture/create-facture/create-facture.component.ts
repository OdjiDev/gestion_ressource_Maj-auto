
import { ProduitDto } from './../../../../../classes/produit-dto';
import { FournisseurService } from 'src/app/services/fournisseur.service';
import { ProduitService } from 'src/app/services/produit.service';
import { Component, OnInit } from '@angular/core';
import { FactureDto } from 'src/app/classes/facture-dto';
import { FactureService } from 'src/app/services/facture.service';
import { Router } from '@angular/router';
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LigneFactureDto } from 'src/app/classes/lignefacture-dto';
import { LigneFactureService } from 'src/app/services/ligneFacture.service';





@Component({
  selector: 'app-create-facture',
  templateUrl: './create-facture.component.html',
  styleUrls: ['./create-facture.component.css']
})
 export class CreateFactureComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
 }
