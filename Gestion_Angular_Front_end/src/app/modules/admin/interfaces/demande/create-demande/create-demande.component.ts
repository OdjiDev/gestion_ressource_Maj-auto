import { BureauService } from 'src/app/services/bureau.service';
import { BureauDto } from 'src/app/classes/bureau-dto';
import { Component, OnInit } from '@angular/core';
import { DemandeDto } from 'src/app/classes/demande-dto';
import { DemandeService } from 'src/app/services/demande.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-demande',
  templateUrl: './create-demande.component.html',
  styleUrls: ['./create-demande.component.css']
})
export class CreateDemandeComponent implements OnInit {

bureau: BureauDto[] = [];
demandeDto: DemandeDto = new DemandeDto();
    constructor(private demandeService:DemandeService,private bureauService:BureauService,
      private router: Router) { }

    ngOnInit(): void {
    }


  getBureaus() {
    this.bureauService.getBureaus()
      .subscribe(data => {
        this.bureau = data;
        console.log("Toutes les bureaus: ", this.bureau);
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
}
