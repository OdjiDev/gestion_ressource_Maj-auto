import { Component, OnInit } from '@angular/core';
import { DepartementDto } from 'src/app/classes/departement-dto';
import { DepartementService } from 'src/app/services/departement.service';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'app-create-departement',
  templateUrl: './create-departement.component.html',
  styleUrls: ['./create-departement.component.css']
})
export class CreateDepartementComponent implements OnInit {

 departementDto:DepartementDto = new DepartementDto();

  constructor(private departementService:DepartementService,
              //private bureauService: BureauService
  ) { }

  ngOnInit() {
    // this.getDepartements();

  }

  // getDepartements() {
  //   this.departementService.getDepartementList().subscribe((data: DepartementDto[]
  //   ) =>
  //     {
  //       this.departements = data;
  //       console.log("Toutes les departements: ", this.getDepartements);
  //     }
  //   )
  //  }

  onSubmit() {
    this.departementService.addDepartement(this.departementDto)
      .subscribe(response => {
        console.log('Departement créé avec succès!');
        // Optionally, clear the form or redirect to another page
      },
      error => {
        console.error('Erreur lors de la création du departement:', error);
      });
  }
}
