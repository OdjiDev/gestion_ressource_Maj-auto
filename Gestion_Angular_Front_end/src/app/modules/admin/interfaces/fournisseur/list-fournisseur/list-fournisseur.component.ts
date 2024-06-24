
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FournisseurDto } from 'src/app/classes/fournisseur-dto';
import {FournisseurService } from 'src/app/services/fournisseur.service';

@Component({
  selector: 'app-list-fournisseur',
  templateUrl: './list-fournisseur.component.html',
  styleUrls: ['./list-fournisseur.component.css']
})
export class ListFournisseurComponent implements OnInit {

 fournisseurDtos:FournisseurDto[] = [];

  constructor(private fournisseurService:FournisseurService,
    private router: Router) { }

  ngOnInit(): void {
    this.getFournisseurs();
  }

  private getFournisseurs(){
    this.fournisseurService.getFournisseurs().subscribe(data => {
      this.fournisseurDtos = data;
    });
  }
onCreateFournisseur()
{
  this.router.navigate(['admin/addfournisseur']);
}
 fournisseurDetails(id: number){
    this.router.navigate(['detailsfournisseur', id]);
  }

  updateFournisseur(id: number){
    this.router.navigate(['updatefournisseur', id]);
  }

  deleteFournisseur(id: number){
    this.fournisseurService.deleteFournisseur(id).subscribe( data => {
      console.log(data);
      this.getFournisseurs();
    })
  }
}
