import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DepartementDto } from '../classes/departement-dto';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

 private baseURL= environment.baseURL+ "departements";

  constructor(private httpClient: HttpClient) { }

  getDepartements(): Observable<DepartementDto[]>{
    return this.httpClient.get<DepartementDto[]>(`${this.baseURL}/list`);
  }

  getDepartementById(id: number): Observable<DepartementDto>{
    return this.httpClient.get<DepartementDto>(`${this.baseURL}/${id}`);
  }

  addDepartement(departementDto: DepartementDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, departementDto);
  }


  // updateDepartement(id: number, departementDto: DepartementDto): Observable<Object>{
  //   return this.httpClient.put(`${this.baseURL}/${id}`, DepartementDto);
  // }



  updateDepartement(id: number, departement: DepartementDto): Observable<DepartementDto> {
    return this.httpClient.put<DepartementDto>(`${this.baseURL}/${id}`, departement);
  }


  deleteDepartement(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
