import { ProduitService } from 'src/app/services/produit.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AvarieDto } from 'src/app/classes/avarie-dto';
import { ProduitDto } from 'src/app/classes/produit-dto';
import { AvarieService } from 'src/app/services/avarie.service';

@Component({
  selector: 'app-list-avarie',
  templateUrl: './list-avarie.component.html',
  styleUrls: ['./list-avarie.component.css']
})
export class ListAvarieComponent implements OnInit {


 produitDtos:ProduitDto[] = [];
avarieDtos:AvarieDto[] = [];

  constructor(private avarieService: AvarieService,private produitService: ProduitService,
    private router: Router) { }

  ngOnInit(): void {
    this.getProduits();
    this.getAvaries();
  }

  private getProduits(){
    this.produitService.getProduits().subscribe(data => {
      this.produitDtos = data;
    });
  }

  private getAvaries(){
    this.avarieService.getAvaries().subscribe(data => {
      this.avarieDtos = data;
    });
  }
onCreateAvarie()
{
  this.router.navigate(['admin/addavarie']);
}
  avarieDetails(id: number){
    this.router.navigate(['detailsavarie', id]);
  }

  updateAvarie(id: number){
    this.router.navigate(['updateavarie', id]);
  }

  deleteAvarie(id: number){
    this.avarieService.deleteAvarie(id).subscribe( data => {
      console.log(data);
      this.getAvaries();
    })
  }
}




