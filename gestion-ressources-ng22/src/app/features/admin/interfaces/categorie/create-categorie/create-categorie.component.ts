import { Component, OnInit } from '@angular/core';
import { CategorieDto } from '..\..\..\..\@app/core/models/categorie-dto'; // Assuming your DTO path
import { CategorieService } from '..\..\..\..\@app/core/services/categorie.service';
import { Router } from '@angular/router';
import {  NgForm }from '@angular/forms';
@Component({
  standalone: true,
  selector: 'app-create-categorie',
  templateUrl: './create-categorie.component.html',
  styleUrls: ['./create-categorie.component.css']
})
export class CreateCategorieComponent implements OnInit {

    categorieDto: CategorieDto = new CategorieDto();
    constructor(private categorieService:CategorieService,
      private router: Router) { }

    ngOnInit(): void {
    }

    saveCategorie(){
      this.categorieService.addCategorie(this.categorieDto).subscribe( data =>{
        console.log(data);
        this.goTocategorieList();
      },
      error => console.log(error));
    }

    goTocategorieList(){
      this.router.navigate(['admin/listcategorie']);
    }

    onSubmit(){
      console.log(this.categorieDto);
      this.saveCategorie();
    }
  }

