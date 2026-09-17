
import { CategorieService } from '@app/core/services/categorie.service';
import { CategorieDto } from '@app/features/categories/categorie.model';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
@Component({
  standalone: true,
  imports: [CommonModule, RouterModule],
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
  onCreateCategorie(){
    this.router.navigate(['admin/addcategorie']);
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
