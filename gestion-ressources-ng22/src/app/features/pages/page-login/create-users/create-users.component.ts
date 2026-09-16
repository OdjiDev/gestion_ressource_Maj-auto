import { Component, OnInit } from '@angular/core';
import { Users } from '..\..\..\@app/core/models/users';
import { Router } from '@angular/router';
import { LoginuserService } from '..\..\..\@app/core/services/loginuser.service';
@Component({
  standalone: true,
  selector: 'app-create-users',
  templateUrl: './create-users.component.html',
  styleUrls: ['./create-users.component.css']
})
export class CreateUsersComponent implements OnInit {


  users: Users = {
    userId: '', password: '',
    repeatpassord: '',
    role: ''
  };

  constructor(private loginuserService: LoginuserService, private router: Router){}
onSubmit() {
  this.router.navigate(['/produit']);
}



  ngOnInit(): void {
  }

}
