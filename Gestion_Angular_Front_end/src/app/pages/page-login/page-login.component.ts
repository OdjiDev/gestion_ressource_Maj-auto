

import { Component, OnInit } from '@angular/core';
import { LoginuserService } from '../../services/loginuser.service';
import { Router } from '@angular/router';
import { Users } from '../../classes/users';
import { UsersDto } from '../../classes/users-dto';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent implements OnInit {
  users: Users = {
    userId: '', password: '',
    repeatpassord: '',
    roles: ''
  };

  constructor(private loginuserService: LoginuserService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    const { userId, password } = this.users;

    this.loginuserService.loginUsers(this.users)
      .subscribe(
        (data) => {
          // Successful login: Redirect to dashboard
          this.router.navigate(['/produit']);
        },
        (error) => {
          // Handle login error
          console.error('Login error:', error);
          alert('Login failed. Please check your username and password.');
        }
      );
  }
}
