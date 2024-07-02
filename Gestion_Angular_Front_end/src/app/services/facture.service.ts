

import { FactureDto } from '../classes/facture-dto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FactureService {

 private baseURL= environment.baseURL+ "factures";

  constructor(private httpClient: HttpClient) { }

  getFactures(): Observable<FactureDto[]>{
    return this.httpClient.get<FactureDto[]>(`${this.baseURL}/list`);
  }

  getFactureById(id: number): Observable<FactureDto>{
    return this.httpClient.get<FactureDto>(`${this.baseURL}/${id}`);
  }


  addFacture(factureDto: FactureDto): Observable<FactureDto>{
    return this.httpClient.post<FactureDto>(`${this.baseURL}`, factureDto);
  }

  updateFacture(id: number, factureDto: FactureDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, FactureDto);
  }

  deleteFacture(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
