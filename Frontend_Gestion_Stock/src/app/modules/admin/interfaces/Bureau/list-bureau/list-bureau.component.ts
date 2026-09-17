import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BureauDto } from 'src/app/classes/bureau-dto';
import { BureauService } from 'src/app/services/bureau.service';

@Component({
  selector: 'app-list-bureau',
  templateUrl: './list-bureau.component.html',
  styleUrls: ['./list-bureau.component.css']
})
export class ListBureauComponent implements OnInit {


  bureauDtos: BureauDto[] = [];

  constructor(private bureauService: BureauService,
    private router: Router) { }

  ngOnInit(): void {
    this.getBureaus();
  }

  private getBureaus(){
    this.bureauService.getBureaus().subscribe(data => {
      this.bureauDtos = data;
    });
  }
onCreateBureau()
{
  this.router.navigate(['admin/addbureau']);
}
  bureauDetails(id: number){
    this.router.navigate(['detailsbureau', id]);
  }

  updateBureau(id: number){
    this.router.navigate(['updatebureau', id]);
  }

  deleteBureau(id: number){
    this.bureauService.deleteBureau(id).subscribe( data => {
      console.log(data);
      this.getBureaus();
    })
  }
}



