import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FournisseurDto } from '../classes/fournisseur-dto';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {

 private baseURL= environment.baseURL+ "fournisseurs";

  constructor(private httpClient: HttpClient) { }

  getFournisseurs(): Observable<FournisseurDto[]>{
    return this.httpClient.get<FournisseurDto[]>(`${this.baseURL}/list`);
  }

  getFournisseurById(id: number): Observable<FournisseurDto>{
    return this.httpClient.get<FournisseurDto>(`${this.baseURL}/${id}`);
  }

  addFournisseur(fournisseurDto: FournisseurDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, fournisseurDto);
  }


  updateFournisseur(id: number, fournisseurDto: FournisseurDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, FournisseurDto);
  }

  deleteFournisseur(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
