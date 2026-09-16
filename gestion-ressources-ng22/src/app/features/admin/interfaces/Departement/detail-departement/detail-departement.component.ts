import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { DepartementDto } from '..\..\..\..\@app/core/models/departement-dto';
import { DepartementService } from '..\..\..\..\@app/core/services/departement.service';

@Component({
  standalone: true,
  imports: [RouterModule],
  selector: 'app-detail-departement',
  templateUrl: './detail-departement.component.html',
  styleUrls: ['./detail-departement.component.css']
})
export class DetailDepartementComponent implements OnInit {

  id!: number;
departementDto: DepartementDto= new DepartementDto();
  constructor(private route: ActivatedRoute, private departementService: DepartementService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.departementDto = new DepartementDto();
    this.departementService.getDepartementById(this.id).subscribe( data => {
      this.departementDto = data;
    });
  }

}
