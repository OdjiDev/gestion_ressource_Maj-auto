import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { MagasinDto } from '../models/magasin.model';

@Injectable({ providedIn: 'root' })
export class MagasinService {
  private readonly httpClient = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/magasins`;

  getMagasins(): Observable<MagasinDto[]> {
    return this.httpClient.get<MagasinDto[]>(this.baseURL);
  }
  getMagasinById(id: number): Observable<MagasinDto> {
    return this.httpClient.get<MagasinDto>(`${this.baseURL}/${id}`);
  }
  addMagasin(dto: MagasinDto): Observable<MagasinDto> {
    return this.httpClient.post<MagasinDto>(this.baseURL, dto);
  }
  updateMagasin(id: number, dto: MagasinDto): Observable<MagasinDto> {
    return this.httpClient.put<MagasinDto>(`${this.baseURL}/${id}`, dto);
  }
  deleteMagasin(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${id}`);
  }
}
