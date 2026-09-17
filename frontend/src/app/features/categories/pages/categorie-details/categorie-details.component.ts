import { CategorieService } from '@app/core/services/categorie.service';
import { CategorieDto } from '@app/features/categories/categorie.model';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
@Component({
  standalone: true,
  imports: [RouterModule],
  selector: 'app-categorie-details',
  templateUrl: './categorie-details.component.html',
  styleUrls: ['./categorie-details.component.css']
})
export class CategorieDetailsComponent implements OnInit {

  id: number=1;

  categorieDto: CategorieDto = new CategorieDto;

  constructor(private route: ActivatedRoute, private categorieService: CategorieService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.categorieDto = new CategorieDto();
    this.categorieService.getCategorieById(this.id).subscribe( data => {
      this.categorieDto = data;
    });
  }

}
