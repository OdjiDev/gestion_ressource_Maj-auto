import { ProduitService } from 'src/app/services/produit.service';
import { Component, OnInit } from '@angular/core';
import { FactureDto } from 'src/app/classes/facture-dto';
import { ProduitDto } from 'src/app/classes/produit-dto';
import { FactureService } from 'src/app/services/facture.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-facture',
  templateUrl: './create-facture.component.html',
  styleUrls: ['./create-facture.component.css']
})
export class CreateFactureComponent implements OnInit {


Produits: ProduitDto[]=[];

factureDto: FactureDto = new FactureDto();
    constructor(private factureService:FactureService,private produitService:ProduitService,
      private router: Router) { }

    ngOnInit(): void {
    }

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
          this.Produits = data;
          console.log("Toutes les produits: ", this.Produits);
        });
    }

  }
