import { RoleService } from '@app/services/role.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonelDto } from '..\..\..\..\@app/core/models/personel-dto';
import { RoleDto } from '..\..\..\..\@app/core/models/role-dto';
import { PersonelService } from '..\..\..\..\@app/core/services/personel.service';
@Component({
  standalone: true,
  selector: 'app-create-personel',
  templateUrl: './create-personel.component.html',
  styleUrls: ['./create-personel.component.css']
})
export class CreatePersonelComponent implements OnInit {
  roles: RoleDto[] = [];
personelDto: PersonelDto = new PersonelDto();
    constructor(private personelService:PersonelService,private roleService:RoleService,
      private router: Router) { }

    ngOnInit(): void {
      this.getRoles();
    }
    getRoles() {
      this.roleService.getRoles()
        .subscribe(data => {
          this.roles = data;
          console.log("Toutes les roles: ", this.roles);
        });
      }

    savePersonel(){
      this.personelService.addPersonel(this.personelDto).subscribe( data =>{
        console.log(data);
        this.goTopersonelList();
      },
      error => console.log(error));
    }

    goTopersonelList(){
      this.router.navigate(['admin/listpersonel']);
    }

    onSubmit(){
      console.log(this.personelDto);
      this.savePersonel();
    }



  }

