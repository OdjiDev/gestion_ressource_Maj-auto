import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { DepartementDto } from '../models/departement.model';

@Injectable({ providedIn: 'root' })
export class DepartementService {
  private readonly httpClient = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/departements`;
  getDepartements(): Observable<DepartementDto[]> {
    return this.httpClient.get<DepartementDto[]>(this.baseURL);
  }
  getDepartementById(id: number): Observable<DepartementDto> {
    return this.httpClient.get<DepartementDto>(`${this.baseURL}/${id}`);
  }
  addDepartement(dto: DepartementDto): Observable<DepartementDto> {
    return this.httpClient.post<DepartementDto>(this.baseURL, dto);
  }
  updateDepartement(id: number, dto: DepartementDto): Observable<DepartementDto> {
    return this.httpClient.put<DepartementDto>(`${this.baseURL}/${id}`, dto);
  }
  deleteDepartement(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${id}`);
  }
}
