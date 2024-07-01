import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DemandeDto } from '../classes/demande-dto';



@Injectable({
  providedIn: 'root'
})
export class DemandeService {

 private baseURL= environment.baseURL+ "demandes";

  constructor(private httpClient: HttpClient) { }

  getDemandes(): Observable<DemandeDto[]>{
    return this.httpClient.get<DemandeDto[]>(`${this.baseURL}/list`);
  }

  getDemandeById(id: number): Observable<DemandeDto>{
    return this.httpClient.get<DemandeDto>(`${this.baseURL}/${id}`);
  }

  addDemande(demandeDto: DemandeDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, demandeDto);
  }

  updateDemande(id: number, demandeDto: DemandeDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, DemandeDto);
  }




  deleteDemande(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
