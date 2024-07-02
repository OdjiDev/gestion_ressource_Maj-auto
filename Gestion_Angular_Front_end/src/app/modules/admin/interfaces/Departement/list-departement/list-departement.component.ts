import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DepartementDto } from 'src/app/classes/departement-dto';
import { DepartementService } from 'src/app/services/departement.service';

@Component({
  selector: 'app-list-departement',
  templateUrl: './list-departement.component.html',
  styleUrls: ['./list-departement.component.css']
})
export class ListDepartementComponent implements OnInit {

  departementDtos: DepartementDto[] = [];

  constructor(private departementService: DepartementService,
    private router: Router) { }

  ngOnInit(): void {
    this.getDepartements();
  }

  private getDepartements(){
    this.departementService.getDepartements().subscribe(data => {
      this.departementDtos = data;
    });
  }
onCreateDepartement()
{
  this.router.navigate(['admin/adddepartement']);
}
  departementDetails(id: number){
    this.router.navigate(['admin/detailsdepartement', id]);
  }

  updateDepartement(id: number){
    this.router.navigate(['admin/updatedepartement', id]);
  }

  deleteDepartement(id: number){
    this.departementService.deleteDepartement(id).subscribe( data => {
      console.log(data);
      this.getDepartements();
    })
  }
}





