
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router'; // Import Router
import { ProduitDto } from 'src/app/classes/produit-dto';
import { ProduitService } from 'src/app/services/produit.service';
@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.css']
})
export class ListProduitComponent implements OnInit {


  produits: ProduitDto[] = [];
  produitDto: ProduitDto = new ProduitDto;


constructor(private produitService: ProduitService, private router :Router) { }

ngOnInit(): void {
  this.getProduits();
  this.getProduitsconsole() ;

}

getProduitsconsole() {
  this.produitService.getProduits()
    .subscribe(data => {
      this.produits = data;
      console.log("Toutes les produits: ", this.produits);
    });
  }
getProduits(): void {
  this.produitService.getProduits()
    .subscribe(produits  => this.produits = produits);
    // this.produitDto = data;
    //  console.log("Toutes les produitDto: ", this.produits);

}

onCreateProduit() {
 this.router.navigate(['admin/addproduit']);
}
onDelete(arg0: number) {
throw new Error('Method not implemented.');
}
onEdit(arg0: number) {
throw new Error('Method not implemented.');
}
// onDetails(_t15: ProduitDto) {
//   this.router.navigate(['/details', produit.id])
// throw new Error('Method not implemented.');
// }
onDetails(produit: ProduitDto) {
this.router.navigate(['/details', produit.id]);
}

}
