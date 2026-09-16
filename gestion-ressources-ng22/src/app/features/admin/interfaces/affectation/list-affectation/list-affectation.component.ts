import { PersonelService } from '..\..\..\..\@app/core/services/personel.service';
import { PersonelDto } from '..\..\..\..\@app/core/models/personel-dto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AffectationDto } from '..\..\..\..\@app/core/models/affectation-dto';
import { ProduitDto } from '..\..\..\..\@app/core/models/produit-dto';
import { AffectationService } from '..\..\..\..\@app/core/services/affectation.service';
import { ProduitService } from '..\..\..\..\@app/core/services/produit.service';

@Component({
  standalone: true,
  selector: 'app-list-affectation',
  templateUrl: './list-affectation.component.html',
  styleUrls: ['./list-affectation.component.css']
})
export class ListAffectationComponent implements OnInit {


  produitDtos:ProduitDto[] = [];
  personelDtos:PersonelDto[]=[];
  affectationDtos:AffectationDto[] = [];

    constructor(private affectationService: AffectationService,
      private produitService: ProduitService,
      private personelService:PersonelService,
      private router: Router) { }

    ngOnInit(): void {
      this.getProduits();
      this.getAffectations();
      this.getPersonels();
    }

    getPersonels(){
      this.personelService.getPersonels().subscribe(data => {
        this.personelDtos = data;
      });
    }
     getProduits(){
      this.produitService.getProduits().subscribe(data => {
        this.produitDtos = data;
      });
    }

    private getAffectations(){
      this.affectationService.getAffectations().subscribe(data => {
        this.affectationDtos = data;
      });
    }
  onCreateAffectation()
  {
    this.router.navigate(['admin/addaffectation']);
  }
    affectationDetails(id: number){
      this.router.navigate(['detailsaffectation', id]);
    }

    updateAffectation(id: number){
      this.router.navigate(['admin/updateaffectation', id]);
    }

    deleteAffectation(id: number){
      this.affectationService.deleteAffectation(id).subscribe( data => {
        console.log(data);
        this.getAffectations();
      })
    }
  }




