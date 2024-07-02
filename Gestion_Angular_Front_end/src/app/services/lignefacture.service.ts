import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LigneFactureDto } from '../classes/lignefacture-dto';



@Injectable({
  providedIn: 'root'
})
export class LigneFactureService {

 private baseURL= environment.baseURL+ "lignefactures";

  constructor(private httpClient: HttpClient) { }

  getLignefactures(): Observable<LigneFactureDto[]>{
    return this.httpClient.get<LigneFactureDto[]>(`${this.baseURL}/list`);
  }

  getLignefactureById(id: number): Observable<LigneFactureDto>{
    return this.httpClient.get<LigneFactureDto>(`${this.baseURL}/${id}`);
  }

<<<<<<< HEAD
  addLignefacture(lignefactureDto: LignefactureDto): Observable<LignefactureDto>{
    return this.httpClient.post<LignefactureDto>(`${this.baseURL}`, lignefactureDto);
=======
  addLignefacture(lignefactureDto: LigneFactureDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, lignefactureDto);
>>>>>>> bc417733688935569d4905aaa6d89003519c4272
  }


  updateLignefacture(id: number, lignefactureDto: LigneFactureDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, LigneFactureDto);
  }

  deleteLignefacture(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}'/lignefactures/${id}`);
  }


}
