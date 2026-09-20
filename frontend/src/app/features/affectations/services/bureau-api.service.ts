import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { BureauDto } from '../models/bureau.model';

@Injectable({ providedIn: 'root' })
export class BureauService {
  private readonly httpClient = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/bureaux`;
  getBureaux(): Observable<BureauDto[]> {
    return this.httpClient.get<BureauDto[]>(this.baseURL);
  }
  getBureauById(id: number): Observable<BureauDto> {
    return this.httpClient.get<BureauDto>(`${this.baseURL}/${id}`);
  }
  addBureau(dto: BureauDto): Observable<BureauDto> {
    return this.httpClient.post<BureauDto>(this.baseURL, dto);
  }
  updateBureau(id: number, dto: BureauDto): Observable<BureauDto> {
    return this.httpClient.put<BureauDto>(`${this.baseURL}/${id}`, dto);
  }
  deleteBureau(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${id}`);
  }
}
