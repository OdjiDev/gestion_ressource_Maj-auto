import { BureauService } from '..\..\..\..\../core/services/bureau.service';
import { BureauDto } from '..\..\..\..\../core/models/bureau-dto';
import { Component, OnInit } from '@angular/core';
import { DemandeDto } from '..\..\..\..\../core/models/demande-dto';
import { DemandeService } from '..\..\..\..\../core/services/demande.service';
import { Router } from '@angular/router';
import { PersonelService } from '..\..\..\..\../core/services/personel.service';
import { PersonelDto } from '..\..\..\..\../core/models/personel-dto';

@Component({
  standalone: true,
  selector: 'app-create-demande',
  templateUrl: './create-demande.component.html',
  styleUrls: ['./create-demande.component.css']
})
export class CreateDemandeComponent implements OnInit {

bureaus: BureauDto[] = [];
personels:PersonelDto[]=[];
demandeDto: DemandeDto = new DemandeDto();
    constructor(private demandeService:DemandeService,
      private bureauService:BureauService,
      private personelService:PersonelService,
      private router: Router) { }

    ngOnInit(): void {
      this.getBureaus();
      this.getPersonels();
    }


  getBureaus() {
    this.bureauService.getBureaus()
      .subscribe(data => {
        this.bureaus = data;
        console.log("Toutes les bureaus: ", this.bureaus);
      });
  }

    saveDemande(){
      this.demandeService.addDemande(this.demandeDto).subscribe( data =>{
        console.log(data);
        this.goTodemandeList();
      },
      error => console.log(error));
    }

    goTodemandeList(){
      this.router.navigate(['admin/listdemande']);
    }

    onSubmit(){
      console.log(this.demandeDto);
      this.saveDemande();
    }
    getPersonels() {
      this.personelService.getPersonels()
        .subscribe(data => {
          this.personels = data;
          console.log("Toutes les personels: ", this.personels);
        });
    }
}

