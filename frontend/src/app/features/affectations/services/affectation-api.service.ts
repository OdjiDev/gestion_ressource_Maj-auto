import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { AffectationDto } from '../models/affectation.model';

@Injectable({ providedIn: 'root' })
export class AffectationService {
  private readonly httpClient = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/affectations`;
  getAffectations(): Observable<AffectationDto[]> {
    return this.httpClient.get<AffectationDto[]>(this.baseURL);
  }
  getAffectationById(id: number): Observable<AffectationDto> {
    return this.httpClient.get<AffectationDto>(`${this.baseURL}/${id}`);
  }
  addAffectation(dto: AffectationDto): Observable<AffectationDto> {
    return this.httpClient.post<AffectationDto>(this.baseURL, dto);
  }
  updateAffectation(id: number, dto: AffectationDto): Observable<AffectationDto> {
    return this.httpClient.put<AffectationDto>(`${this.baseURL}/${id}`, dto);
  }
  deleteAffectation(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${id}`);
  }
}
