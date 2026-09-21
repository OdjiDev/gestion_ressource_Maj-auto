import { Injectable } from '@angular/core';
import { SocieteDto } from '@app/core/models/societe-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
@Injectable({
  providedIn: 'root'
})
export class SocieteService {
  private baseURL = environment.baseURL + 'societes';

  constructor(private httpClient: HttpClient) {}

  getSocietes(): Observable<SocieteDto[]> {
    return this.httpClient.get<SocieteDto[]>(`${this.baseURL}/list`);
  }

  getSocieteById(id: number): Observable<SocieteDto> {
    return this.httpClient.get<SocieteDto>(`${this.baseURL}/${id}`);
  }

  addSociete(societeDto: SocieteDto): Observable<object> {
    return this.httpClient.post(`${this.baseURL}`, societeDto);
  }

  updateSociete(id: number, societeDto: SocieteDto): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, SocieteDto);
  }

  deleteSociete(id: number): Observable<object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
