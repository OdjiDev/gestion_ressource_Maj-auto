// import { MagasinService } from '../../../../../services/magasin.service';
// import { CategorieService } from '../../../../../services/categorie.service';
// //import { CreateProduitComponent } from './../../../create-produit/create-produit.component';
// import { Component, OnInit } from '@angular/core';
// import { ProduitDto } from '../../../../../classes/produit-dto';
// import { CategorieDto } from '../../../../../classes/categorie-dto';
//  // Assuming MagasinDto is defined elsewhere
// import { ProduitService } from '../../../../../services/produit.service';
// import { MagasinDto } from 'src/app/classes/magasin-dto';
// import { FormsModule } from '@angular/forms';
// import { RouterModule, Routes } from '@angular/router';


// @Component({
//   standalone: true,
//   selector: 'app-create-produit',
//   templateUrl: './create-produit.component.html',
//   styleUrls: ['./create-produit.component.css'],
//   imports: [
//     FormsModule // Ajouter FormsModule aux imports et le stantone :true pour eviter le probleme de ngModule
//   ]
// })
// export class CreateProduitComponent implements OnInit {

//   produitDto: ProduitDto = new ProduitDto();
//   categories: CategorieDto[] = [];
//   magasins: MagasinDto[] = [];



//   constructor(private produitService: ProduitService,
//               private categorieService: CategorieService,
//               private magasinService: MagasinService,


//   ) { }

//   ngOnInit() {
//     this.getCategories();
//     this.getMagasins();
//   }

//   getCategories() {
//     this.categorieService.getCategories().subscribe((data: CategorieDto[]
//     ) =>
//       {
//         this.categories = data;
//         console.log("Toutes les categories: ", this.categories);
//       }
//     )
//    }

//    getMagasins() {
//     this.magasinService.getMagasins().subscribe((data: MagasinDto[]
//     ) =>
//       {
//         this.magasins = data;
//         console.log("Toutes les magasins: ", this.magasins);
//       }
//     )
//   }

//   onSubmit() {
//     this.produitService.addProduit(this.produitDto)
//       .subscribe(response => {
//         console.log('Produit créé avec succès!');
//         // Optionally, clear the form or redirect to another page
//       },
//       error => {
//         console.error('Erreur lors de la création du produit:', error);
//       });
//   }
// }



import { MagasinService } from '../../../../../services/magasin.service';
import { CategorieService } from '../../../../../services/categorie.service';
import { ProduitService } from '../../../../../services/produit.service';
import { ProduitDto } from '../../../../../classes/produit-dto';
import { CategorieDto } from '../../../../../classes/categorie-dto';
import { MagasinDto } from '../../../../../classes/magasin-dto';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  standalone:true,
  selector: 'app-create-produit',
  templateUrl: './create-produit.component.html',
  styleUrls: ['./create-produit.component.css'],
  imports: [FormsModule] // Import FormsModule for two-way data binding
})
export class CreateProduitComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private magasinService: MagasinService
  ) { }

  produitForm: FormGroup[]=[];





  //constructor(private fb: FormBuilder) { }

  ngOnInit() {
  //   this.produitForm = this.fb.group({
  //     // Define form controls here
  //     codeproduit: ['', Validators.required],
  //     nom: ['', Validators.required],
  //     designation: ['', Validators.required],
  //     quantite: [1, Validators.min(1)], // Set minimum quantity to 1
  //     magasinDto: ['', Validators.required],
  //     categorieDto: ['', Validators.required]
  //   });
  //   this.getCategories();
  //   this.getMagasins();
   }


  produitDto: ProduitDto = new ProduitDto();
  categories: CategorieDto[] = [];
  magasins: MagasinDto[] = [];






  getCategories() {
    this.categorieService.getCategories()
      .subscribe(data => {
        this.categories = data;
        console.log("Toutes les categories: ", this.categories);
      });
  }

  getMagasins() {
    this.magasinService.getMagasins()
      .subscribe(data => {
        this.magasins = data;
        console.log("Toutes les magasins: ", this.magasins);
      });
  }

  onSubmit() {
    this.produitService.addProduit(this.produitDto)
      .subscribe(response => {
        console.log('Produit créé avec succès!');
        // Optionally, clear the form or redirect to another page
      },
      error => {
        console.error('Erreur lors de la création du produit:', error);
      });
  }
}
