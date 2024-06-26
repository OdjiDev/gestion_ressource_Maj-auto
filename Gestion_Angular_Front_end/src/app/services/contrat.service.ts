import { ContratDto } from '../classes/contrat-dto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContratService {

 private baseURL= environment.baseURL+ "contrats";

  constructor(private httpClient: HttpClient) { }

  getContrats(): Observable<ContratDto[]>{
    return this.httpClient.get<ContratDto[]>(`${this.baseURL}/list`);
  }


  getContratById(id: number): Observable<ContratDto>{
    return this.httpClient.get<ContratDto>(`${this.baseURL}/${id}`);
  }

  addContrat(contratDto: ContratDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, contratDto);
  }

  updateContrat(id: number, contratDto: ContratDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, ContratDto);
  }

  deleteContrat(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
