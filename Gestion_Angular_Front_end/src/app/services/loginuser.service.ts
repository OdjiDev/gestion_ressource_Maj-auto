import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from '../classes/users';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class LoginuserService {


  private baseUrl = environment.baseURL+ "login";

  constructor(private httpClient: HttpClient) {}

  loginUsers(users: Users): Observable<object> {
    console.log(users);

    // Create headers with Content-Type set to application/json
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    // Send the POST request with the user data and headers
    return this.httpClient.post(`${this.baseUrl}`, users, { headers });
  }
}
