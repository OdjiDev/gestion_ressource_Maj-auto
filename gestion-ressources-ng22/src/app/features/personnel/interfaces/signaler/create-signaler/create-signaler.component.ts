import { PersonelService } from './../../../../../services/personel.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonelDto } from '..\..\..\..\../core/models/personel-dto';
import { ProduitDto } from '..\..\..\..\../core/models/produit-dto';
import { SignalerDto } from '..\..\..\..\../core/models/signaler-dto';
import { ProduitService } from '..\..\..\..\../core/services/produit.service';
import { SignalerService } from '..\..\..\..\../core/services/signaler-dto.service';

@Component({
  standalone: true,
  selector: 'app-create-signaler',
  templateUrl: './create-signaler.component.html',
  styleUrls: ['./create-signaler.component.css']
})
export class CreateSignalerComponent implements OnInit {

  produits: ProduitDto[]=[];
 personels: PersonelDto[]=[];
  signalerDto: SignalerDto = new SignalerDto();
      constructor(private signalerService:SignalerService,
        private produitService:ProduitService,
        private personelService:PersonelService,
       private router: Router) { }

      ngOnInit(): void {
        this.getProduits();
        this.getPersonels();
      }

      saveSignaler(){
        this.signalerService.addSignaler(this.signalerDto).subscribe( data =>{
          console.log(data);
          this.goTosignalerList();
        },
        error => console.log(error));
      }

      goTosignalerList(){
        this.router.navigate(['personel/listsignaler']);
      }

      onSubmit(){
        console.log(this.signalerDto);
        this.saveSignaler();
      }
      getProduits() {
        this.produitService.getProduits()
          .subscribe(data => {
            this.produits = data;
            console.log("Toutes les produits: ", this.produits);
          });
      }

      getPersonels() {
        this.personelService.getPersonels()
          .subscribe(data => {
            this.personels = data;
            console.log("Toutes les personels: ", this.personels);
          });
      }

    }

