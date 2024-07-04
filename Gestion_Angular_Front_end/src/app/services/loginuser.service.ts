import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UsersDto } from '../classes/users-dto';
import { Users } from '../classes/users';

@Injectable({
  providedIn: 'root'
})
export class LoginuserService {
  private baseURL = environment.baseURL+ "login";

  constructor(private httpClient: HttpClient) {}

  loginUsers(usersDto: UsersDto): Observable<object> {
    console.log(usersDto);

    // Create headers with Content-Type set to application/json
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    // Send the POST request with the users data and headers
    return this.httpClient.post<Object>(`${this.baseURL}`, usersDto, { headers });

  }


  getUserss(): Observable<UsersDto[]>{
    return this.httpClient.get<UsersDto[]>(`${this.baseURL}/list`);
  }

  // getUsersById(id: number): Observable<UsersDto>{
  //   return this.httpClient.get<UsersDto>(`${this.baseURL}/${id}`);
  // }

  addUsers(usersDto: UsersDto): Observable<Object>{
    return this.httpClient.post<Object>(`${this.baseURL}`, usersDto);
  }

}


//   updateUsers(id: number, usersDto: UsersDto): Observable<Object>{
//     return this.httpClient.put<Object>(`${this.baseURL}/${id}`, UsersDto);
//   }

//   deleteUsers(id: number): Observable<Object>{
//     return this.httpClient.delete<Object>(`${this.baseURL}/${id}`);
//   }


// }



//spring security 6
// private baseUrl = environment.baseURL+ "login";

// constructor(private httpClient: HttpClient) {}

// loginUsers(users: Users): Observable<object> {
//   console.log(users);

//   // Create headers with Content-Type set to application/json
//   const headers = new HttpHeaders().set('Content-Type', 'application/json');

//   // Send the POST request with the user data and headers
//   return this.httpClient.post(`${this.baseUrl}`, users, { headers });
// }
// }
