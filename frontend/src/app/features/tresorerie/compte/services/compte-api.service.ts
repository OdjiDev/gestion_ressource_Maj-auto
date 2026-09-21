import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '@env/environment';
import { Compte, CompteCreateRequest } from '../models/compte.model';

@Injectable({ providedIn: 'root' })
export class CompteApiService {
  private readonly http = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/comptes`;

  getAllComptes(): Observable<Compte[]> {
    return this.http.get<any>(`${this.baseURL}/all`).pipe(
      map(res => Array.isArray(res) ? res : (res?.content ?? []))
    );
  }
  getCompteById(id: number): Observable<Compte> {
    return this.http.get<Compte>(`${this.baseURL}/${id}`);
  }
  addCompte(dto: CompteCreateRequest): Observable<Compte> {
    return this.http.post<Compte>(this.baseURL, dto);
  }
  updateCompte(id: number, dto: CompteCreateRequest): Observable<Compte> {
    return this.http.put<Compte>(`${this.baseURL}/${id}`, dto);
  }
  deleteCompte(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }
}
