import { RoleDto } from 'src/app/classes/role-dto';
import { UsersDto } from 'src/app/classes/users-dto';
import { Component, OnInit } from '@angular/core';
import { LoginuserService } from '../../services/loginuser.service';
import { Router } from '@angular/router';
import { Users } from '../../classes/users';


@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent implements OnInit {
  // usersDto: UsersDto[] = [];
  // user: Users = {
  //   userId: '', password: '',
  //   repeatpassord: '',
  //   role: ''
  // };


  constructor(private loginuserService: LoginuserService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
   // const { userIdDto, passwordDto,RoleDto } = this.usersDto;

    // this.loginuserService.loginUsers(this.usersDto)
    //   .subscribe(
    //     (data) => {
    //       // Successful login: Redirect to dashboard
    //       this.router.navigate(['/produit']);
    //     },
    //     (error) => {
    //       // Handle login error
    //       console.error('Login error:', error);
    //       alert('Login failed. Please check your username and password.');
    //     }
    //   );
  }
}
