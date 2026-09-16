import { environment } from '@env/environment';
import { CategorieDto } from '@core/models/categorie-dto';
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CategorieService {

  private readonly httpClient = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/categories`;

  // ==================== LECTURE ====================

  getCategories(): Observable<CategorieDto[]> {
    return this.httpClient.get<CategorieDto[]>(this.baseURL);
  }

  getCategorieById(id: number): Observable<CategorieDto> {
    return this.httpClient.get<CategorieDto>(`${this.baseURL}/${id}`);
  }

  // ==================== ÉCRITURE ====================

  addCategorie(categorieDto: CategorieDto): Observable<CategorieDto> {
    return this.httpClient.post<CategorieDto>(this.baseURL, categorieDto);
  }

  updateCategorie(id: number, categorieDto: CategorieDto): Observable<CategorieDto> {
    return this.httpClient.put<CategorieDto>(`${this.baseURL}/${id}`, categorieDto);
  }

  deleteCategorie(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${id}`);
  }
}
