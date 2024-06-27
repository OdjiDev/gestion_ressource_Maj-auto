import { PersonelDto } from 'src/app/classes/personel-dto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AffectationDto } from 'src/app/classes/affectation-dto';
import { ProduitDto } from 'src/app/classes/produit-dto';
import { AffectationService } from 'src/app/services/affectation.service';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-list-affectation',
  templateUrl: './list-affectation.component.html',
  styleUrls: ['./list-affectation.component.css']
})
export class ListAffectationComponent implements OnInit {


  produitDtos:ProduitDto[] = [];
  personelDto:PersonelDto[]=[];
  affectationDtos:AffectationDto[] = [];

    constructor(private affectationService: AffectationService,private produitService: ProduitService,
      private router: Router) { }

    ngOnInit(): void {
      this.getProduits();
      this.getAffectations();
    }

    private getProduits(){
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
      this.router.navigate(['updateaffectation', id]);
    }

    deleteAffectation(id: number){
      this.affectationService.deleteAffectation(id).subscribe( data => {
        console.log(data);
        this.getAffectations();
      })
    }
  }




