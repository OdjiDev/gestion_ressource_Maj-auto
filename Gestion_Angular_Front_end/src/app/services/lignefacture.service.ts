import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LignefactureDto } from '../classes/lignefacture-dto';

@Injectable({
  providedIn: 'root'
})
export class LignefactureService {

 private baseURL= environment.baseURL+ "lignefactures";

  constructor(private httpClient: HttpClient) { }

  getLignefactures(): Observable<LignefactureDto[]>{
    return this.httpClient.get<LignefactureDto[]>(`${this.baseURL}/list`);
  }

  getLignefactureById(id: number): Observable<LignefactureDto>{
    return this.httpClient.get<LignefactureDto>(`${this.baseURL}/${id}`);
  }

  addLignefacture(lignefactureDto: LignefactureDto): Observable<LignefactureDto>{
    return this.httpClient.post<LignefactureDto>(`${this.baseURL}`, lignefactureDto);
  }


  updateLignefacture(id: number, lignefactureDto: LignefactureDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, LignefactureDto);
  }

  deleteLignefacture(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}'/lignefactures/${id}`);
  }


}
