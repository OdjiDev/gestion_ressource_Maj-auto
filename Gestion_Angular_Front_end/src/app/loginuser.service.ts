// import { LoginComponent } from './login/login.component';
// import { Injectable } from '@angular/core';
// import { HttpClient, HttpErrorResponse } from '@angular/common/http';
// import { Users } from './users';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class LoginuserService {


//   private baseUrl="http://localhost:8080/users/login";

//   constructor(private httpClient:HttpClient  ) {}


//  loginUsers(users:Users):Observable<object>{
//   console.log(users)
//   return this.httpClient.post(`${this.baseUrl}`, Users);
//  }
// };



//Ancien


// import { Injectable } from '@angular/core';
// import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
// import { Users } from './users';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class LoginuserService {

//   private baseUrl = "http://localhost:8080/users/login";

//   constructor(private httpClient: HttpClient) {}

//   loginUsers(users: Users): Observable<object> {
//     console.log(users);

//     // Create headers with Content-Type set to application/json
//     const headers = new HttpHeaders().set('Content-Type', 'application/json');

//     // Send the POST request with the user data and headers
//     return this.httpClient.post(`${this.baseUrl}`, users, { headers });
//   }
// }



