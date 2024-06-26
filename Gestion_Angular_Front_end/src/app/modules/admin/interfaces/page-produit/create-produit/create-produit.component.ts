
import { MagasinService } from '../../../../../services/magasin.service';
import { CategorieService } from '../../../../../services/categorie.service';
import { ProduitService } from '../../../../../services/produit.service';
import { ProduitDto } from '../../../../../classes/produit-dto';
import { CategorieDto } from '../../../../../classes/categorie-dto';
import { MagasinDto } from '../../../../../classes/magasin-dto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  // standalone:true,
  selector: 'app-create-produit',
  templateUrl: './create-produit.component.html',
  styleUrls: ['./create-produit.component.css'],
  //imports: [FormsModule] // Import FormsModule for two-way data binding
})
export class CreateProduitComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private magasinService: MagasinService,
    private router:Router
  ) { }

  produitForm: FormGroup[]=[];





  //constructor(private fb: FormBuilder) { }

  ngOnInit() {

   this.getCategories();
    //this.getMagasins();
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

  // onSubmit() {
  //   this.produitService.addProduit(this.produitDto)
  //     .subscribe(data => {
  //       console.log('Produit créé avec succès!');
  //       console.log(data);
  //       this.goToProduitList();
  //       // Optionally, clear the form or redirect to another page
  //     },
  //     error => {
  //       console.error('Erreur lors de la création du produit:', error);
  //     });
  // }


saveProduit(){
  this.produitService.addProduit(this.produitDto).subscribe( data =>{
    console.log(data);
    console.log('Produit créé avec succès!');
    this.goToproduitList();
  },
  error => console.log(error));
}

goToproduitList(){
  this.router.navigate(['admin/listproduit']);
}

onSubmit(){
  console.log(this.produitDto);
  this.saveProduit();
}
}
