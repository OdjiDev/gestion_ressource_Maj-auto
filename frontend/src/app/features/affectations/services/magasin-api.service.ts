import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '@env/environment';
import { MagasinDto } from '../models/magasin.model';

@Injectable({ providedIn: 'root' })
export class MagasinService {
  private readonly http = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/magasins`;

  getMagasins(): Observable<MagasinDto[]> {
    return this.http.get<any>(this.baseURL).pipe(
      map(res => Array.isArray(res) ? res : (res?.content ?? []))
    );
  }
  getMagasinById(id: number): Observable<MagasinDto> {
    return this.http.get<MagasinDto>(`${this.baseURL}/${id}`);
  }
  addMagasin(dto: MagasinDto): Observable<MagasinDto> {
    return this.http.post<MagasinDto>(this.baseURL, dto);
  }
  updateMagasin(id: number, dto: MagasinDto): Observable<MagasinDto> {
    return this.http.put<MagasinDto>(`${this.baseURL}/${id}`, dto);
  }
  deleteMagasin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }
}
