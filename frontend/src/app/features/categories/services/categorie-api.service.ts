import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { CategorieDto } from '../models/categorie.model';

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}

@Injectable({ providedIn: 'root' })
export class CategorieService {

  private readonly http = inject(HttpClient);
  private readonly baseURL = `${environment.baseURL}/categories`;

  /**
   * Liste paginée (standard industrie).
   */
  getCategoriesPaginated(page = 0, size = 20, sort = 'nom'): Observable<Page<CategorieDto>> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort);
    return this.http.get<Page<CategorieDto>>(this.baseURL, { params });
  }

  /**
   * Liste complète (pour les selects / autocomplete).
   */
  getAllCategories(): Observable<CategorieDto[]> {
    return this.http.get<CategorieDto[]>(`${this.baseURL}/all`);
  }

  getCategorieById(id: number): Observable<CategorieDto> {
    return this.http.get<CategorieDto>(`${this.baseURL}/${id}`);
  }

  addCategorie(dto: CategorieDto): Observable<CategorieDto> {
    return this.http.post<CategorieDto>(this.baseURL, dto);
  }

  updateCategorie(id: number, dto: CategorieDto): Observable<CategorieDto> {
    return this.http.put<CategorieDto>(`${this.baseURL}/${id}`, dto);
  }

  deleteCategorie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }
}
