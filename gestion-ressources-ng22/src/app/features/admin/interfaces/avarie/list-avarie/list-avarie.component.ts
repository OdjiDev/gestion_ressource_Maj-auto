import { ProduitService } from '..\..\..\..\@app/core/services/produit.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AvarieDto } from '..\..\..\..\@app/core/models/avarie-dto';
import { ProduitDto } from '..\..\..\..\@app/core/models/produit-dto';
import { AvarieService } from '..\..\..\..\@app/core/services/avarie.service';

@Component({
  standalone: true,
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




