import { ActivatedRoute, Router } from '@angular/router';
import { PersonelService } from 'src/app/services/personel.service';
import { ProduitService } from 'src/app/services/produit.service';
import { Component, OnInit } from '@angular/core';
import { AffectationDto } from 'src/app/classes/affectation-dto';
import { PersonelDto } from 'src/app/classes/personel-dto';
import { ProduitDto } from 'src/app/classes/produit-dto';
import { AffectationService } from 'src/app/services/affectation.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-update-affectation',
  templateUrl: './update-affectation.component.html',
  styleUrls: ['./update-affectation.component.css']
})
export class UpdateAffectationComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

//   id:number=0;
//   affectation: AffectationDto=new AffectationDto;
//   produits: ProduitDto[]=[];
//   personels: PersonelDto[]=[];

//   constructor(private affectationService: AffectationService,
//               private produitService: ProduitService,
//               private personelService:PersonelService,
//               private router:Router,
//               private route: ActivatedRoute,
//   ) { }

//   ngOnInit(): void {
//     this.id = this.route.snapshot.params['id'];
//     this.affectationService.getAffectationById(this.id).subscribe(data => {
//       this.affectation = data;
//     }, error => console.log(error));


//     this.produitService.getProduits().subscribe(produits => this.produits = produits);
//     this.personelService.getPersonels().subscribe(personels => this.personels = personels);
//   }




//   updateAffectation(): void {
//     const headers = new HttpHeaders().set('Content-Type', 'application/json');
//     this.affectationService.updateAffectation(this.affectation.id, this.affectation)
//       .subscribe(() => {
//         alert('Affectation mise à jour avec succès!');
//       }, error => {
//         console.error(error);
//         alert('Échec de la mise à jour de l\'affectation');
//       });
//   }


// onSubmit(){
//   this.affectationService.updateAffectation(this.id, this.affectation).subscribe( data =>{
//     this.goToAffectationList();
//   }
//   , error => console.log(error));
// }

// goToAffectationList(){
//   this.router.navigate(['../listaffectation']);
// }
// }
}
