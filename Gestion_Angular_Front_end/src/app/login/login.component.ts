import { HttpHeaders } from '@angular/common/http';


import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { Users } from '../classes/users';
import { LoginuserService } from '../services/loginuser.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent implements OnInit {
users: Users= new Users();

  constructor(private loginuserService:LoginuserService,private router:Router) { }

  ngOnInit(): void {
  }

usersLogin() {
  console.log(this.users);

  const headers = new HttpHeaders().set('Content-Type', 'application/json');

  // Send the request with the headers
  this.loginuserService.loginUsers(this.users)
    .subscribe(data => {
      alert("Login successfully");
      this.router.navigate(['']);
    }, error => {
      alert("Sorry please enter correct username or password");
    });
}
}
