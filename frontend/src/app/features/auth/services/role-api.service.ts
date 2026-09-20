import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { RoleDto } from '@app/core/models/role-dto';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private baseURL = environment.baseURL + 'roles';

  constructor(private httpClient: HttpClient) {}

  getRoles(): Observable<RoleDto[]> {
    return this.httpClient.get<RoleDto[]>(`${this.baseURL}/list`);
  }

  getRoleById(id: number): Observable<RoleDto> {
    return this.httpClient.get<RoleDto>(`${this.baseURL}/${id}`);
  }

  addRole(roleDto: RoleDto): Observable<object> {
    return this.httpClient.post(`${this.baseURL}`, roleDto);
  }

  updateRole(id: number, roleDto: RoleDto): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, RoleDto);
  }

  deleteRole(id: number): Observable<object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
