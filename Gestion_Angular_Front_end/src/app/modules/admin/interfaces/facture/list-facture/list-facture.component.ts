import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FactureDto } from 'src/app/classes/facture-dto';
import { ProduitDto } from 'src/app/classes/produit-dto';
import { FactureService } from 'src/app/services/facture.service';

@Component({
  selector: 'app-list-facture',
  templateUrl: './list-facture.component.html',
  styleUrls: ['./list-facture.component.css']
})
export class ListFactureComponent implements OnInit {


factureDtos: FactureDto[] = [];
produits:ProduitDto[] = [];
constructor(private factureService: FactureService,
  private router: Router) { }

ngOnInit(): void {
  this.getFactures();
}

private getFactures(){
  this.factureService.getFactures().subscribe(data => {
    this.factureDtos = data;
  });
}
onCreateFacture()
{
this.router.navigate(['admin/addfacture']);
}
factureDetails(id: number){
  this.router.navigate(['detailsfacture', id]);
}

updateFacture(id: number){
  this.router.navigate(['updatefacture', id]);
}

deleteFacture(id: number){
  this.factureService.deleteFacture(id).subscribe( data => {
    console.log(data);
    this.getFactures();
  })
}
}


