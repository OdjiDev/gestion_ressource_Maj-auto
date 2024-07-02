import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UsersDto } from '../classes/users-dto';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

 private baseURL= environment.baseURL+ "userss";

  constructor(private httpClient: HttpClient) { }

  getUserss(): Observable<UsersDto[]>{
    return this.httpClient.get<UsersDto[]>(`${this.baseURL}/list`);
  }

  getUsersById(id: number): Observable<UsersDto>{
    return this.httpClient.get<UsersDto>(`${this.baseURL}/${id}`);
  }

  addUsers(usersDto: UsersDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, usersDto);
  }


  updateUsers(id: number, usersDto: UsersDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, UsersDto);
  }

  deleteUsers(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
