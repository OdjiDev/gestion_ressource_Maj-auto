import { SocieteDto } from './../../../../../classes/societe-dto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocieteService } from 'src/app/services/societe.service';

@Component({
  selector: 'app-list-societe',
  templateUrl: './list-societe.component.html',
  styleUrls: ['./list-societe.component.css']
})
export class ListSocieteComponent implements OnInit {


  societeDtoDto: SocieteDto = new SocieteDto;
 societes: SocieteDto []=[];
  constructor(private societeService: SocieteService,
    private router: Router) { }

  ngOnInit(): void {
    this.getSocietes();
  }
  getSocietes(): void {
    this.societeService.getSocietes()
      .subscribe(societes  => this.societes = societes);
  }

onCreateSociete()
{
  this.router.navigate(['admin/addsociete']);
}
  societeDetails(id: number){
    this.router.navigate(['detailsociete', id]);
  }

  updateSociete(id: number){
    this.router.navigate(['updatesociete', id]);
  }

  deleteSociete(id: number){
    this.societeService.deleteSociete(id).subscribe( data => {
      console.log(data);

    })
  }
}




