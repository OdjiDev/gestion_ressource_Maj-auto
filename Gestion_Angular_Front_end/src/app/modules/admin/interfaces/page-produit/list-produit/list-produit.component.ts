
import { Component, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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
  this.getProducts();
}

getProducts(): void {
  this.produitService.getProduits()
    .subscribe(produits  => this.produits = produits);
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
