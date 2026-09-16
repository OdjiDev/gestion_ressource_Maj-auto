import { PersonelService } from '@app/services/personel.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonelDto } from '..\..\..\..\@app/core/models/personel-dto';
import { ProduitDto } from '..\..\..\..\@app/core/models/produit-dto';
import { SignalerDto } from '..\..\..\..\@app/core/models/signaler-dto';
import { ProduitService } from '..\..\..\..\@app/core/services/produit.service';
import { SignalerService } from '..\..\..\..\@app/core/services/signaler-dto.service';

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

