import { LignefactureDto } from './../../../../../classes/lignefacture-dto';
import { ProduitDto } from './../../../../../classes/produit-dto';
import { FournisseurService } from 'src/app/services/fournisseur.service';
import { Component, OnInit } from '@angular/core';
import { FactureDto } from 'src/app/classes/facture-dto';
import { FactureService } from 'src/app/services/facture.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';

@Component({
  selector: 'app-create-facture',
  templateUrl: './create-facture.component.html',
  styleUrls: ['./create-facture.component.css'],
})
export class CreateFactureComponent implements OnInit {


produits: ProduitDto[]=[];
lignefactureDto: LignefactureDto = new LignefactureDto()
fournisseurs: FournisseurDto[]=[];

factureDto: FactureDto = new FactureDto();
  // facture: any;
    constructor(private factureService:FactureService,
      private produitService:ProduitService,
      private fournisseurService:FournisseurService,
      private router: Router) { }

    ngOnInit(): void {
      this.getProduits();
      this.getFournisseurs();
    }
    addLineItem(produitDto: ProduitDto, quantite: number): void {
      const ligneItem: LignefactureDto = {
        produitDto: produitDto,
        quantite: quantite,
        total: produitDto.prix * quantite,
        id: 0,
        push: function (ligneItem: LignefactureDto): void {
          throw new Error('Function not implemented.');
        }
      };
      this.factureDto.lignefactureDto.push(ligneItem);
      //this.updateTotal();

    }



    // updateTotal(): void {
    //   this.factureDto.total = 0;
    //   for (const item of this.factureDto.lignefactureDto.push(ligneItem)){
    //     this.factureDto.total += item.total;
    //   }



    saveFacture(){
      this.factureService.addFacture(this.factureDto).subscribe( data =>{
        console.log(data);
        this.goTofactureList();
      },
      error => console.log(error));
    }

    goTofactureList(){
      this.router.navigate(['admin/listfacture']);
    }

    onSubmit(){
      console.log(this.factureDto);
      this.saveFacture();
    }
    getProduits() {
      this.produitService.getProduits()
        .subscribe(data => {
          this.produits = data;
          console.log("Toutes les produits: ", this.produits);
        });
    }
    getFournisseurs() {
      this.fournisseurService.getFournisseurs()
        .subscribe(data => {
          this.fournisseurs = data;
          console.log("Toutes les fournisseurs: ", this.produits);
        });
    }
  }

