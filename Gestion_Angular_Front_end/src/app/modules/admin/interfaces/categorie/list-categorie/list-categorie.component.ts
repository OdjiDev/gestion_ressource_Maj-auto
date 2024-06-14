
import { CategorieService } from '../../../../../services/categorie.service';
import { CategorieDto } from '../../../../../classes/categorie-dto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-list-categorie',
  templateUrl: './list-categorie.component.html',
  styleUrls: ['./list-categorie.component.css']
})
export class ListCategorieComponent implements OnInit {

  categorieDtos: CategorieDto[] = [];

  constructor(private categorieService: CategorieService,
    private router: Router) { }

  ngOnInit(): void {
    this.getCategories();
  }

  private getCategories(){
    this.categorieService.getCategories().subscribe(data => {
      this.categorieDtos = data;
    });
  }

  CategorieDetails(id: number){
    this.router.navigate(['detailscategorie', id]);
  }

  updateCategorie(id: number){
    this.router.navigate(['updatecategorie', id]);
  }

  deleteCategorie(id: number){
    this.categorieService.deleteCategorie(id).subscribe( data => {
      console.log(data);
      this.getCategories();
    })
  }
}
