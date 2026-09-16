import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContratDto } from '..\..\..\..\../core/models/contrat-dto';
import { ContratService } from '..\..\..\..\../core/services/contrat.service';

@Component({
  standalone: true,
  selector: 'app-list-contrat',
  templateUrl: './list-contrat.component.html',
  styleUrls: ['./list-contrat.component.css']
})
export class ListContratComponent implements OnInit {


  contratDtos: ContratDto[] = [];

  constructor(private contratService: ContratService,
    private router: Router) { }

  ngOnInit(): void {
    this.getContrats();
  }

  private getContrats(){
    this.contratService.getContrats().subscribe(data => {
      this.contratDtos = data;
    });
  }
onCreateContrat()
{
  this.router.navigate(['admin/addcontrat']);
}
  contratDetails(id: number){
    this.router.navigate(['detailscontrat', id]);
  }

  updateContrat(id: number){
    this.router.navigate(['updatecontrat', id]);
  }

  deleteContrat(id: number){
    this.contratService.deleteContrat(id).subscribe( data => {
      console.log(data);
      this.getContrats();
    })
  }
}



