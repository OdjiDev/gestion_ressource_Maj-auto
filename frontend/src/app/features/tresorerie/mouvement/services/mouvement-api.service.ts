import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '@env/environment';
import { Mouvement, MouvementCreateRequest, SoldeDto } from '../models/mouvement.model';

@Injectable({ providedIn: 'root' })
export class MouvementApiService {

  private readonly http = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/mouvements`;

  getAllMouvements(): Observable<Mouvement[]> {
    return this.http.get<any>(`${this.baseURL}/all`).pipe(
      map(res => Array.isArray(res) ? res : (res?.content ?? []))
    );
  }

  getMouvementsByCompte(compteId: number): Observable<Mouvement[]> {
    return this.http.get<Mouvement[]>(`${this.baseURL}/compte/${compteId}`);
  }

  getMouvementById(id: number): Observable<Mouvement> {
    return this.http.get<Mouvement>(`${this.baseURL}/${id}`);
  }

  getSolde(compteId: number): Observable<SoldeDto> {
    return this.http.get<SoldeDto>(`${this.baseURL}/solde/${compteId}`);
  }

  addMouvement(dto: MouvementCreateRequest): Observable<Mouvement> {
    return this.http.post<Mouvement>(this.baseURL, dto);
  }

  updateMouvement(id: number, dto: MouvementCreateRequest): Observable<Mouvement> {
    return this.http.put<Mouvement>(`${this.baseURL}/${id}`, dto);
  }

  deleteMouvement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }
}
