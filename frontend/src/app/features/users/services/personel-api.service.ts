import { Injectable } from '@angular/core';
import { PersonelDto } from '..\@app/core/models/personel-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
@Injectable({
  providedIn: 'root'
})
export class PersonelService {
  private baseURL = environment.baseURL + 'personels';

  constructor(private httpClient: HttpClient) {}

  getPersonels(): Observable<PersonelDto[]> {
    return this.httpClient.get<PersonelDto[]>(`${this.baseURL}/list`);
  }

  getPersonelById(id: number): Observable<PersonelDto> {
    return this.httpClient.get<PersonelDto>(`${this.baseURL}/${id}`);
  }

  addPersonel(personelDto: PersonelDto): Observable<object> {
    return this.httpClient.post(`${this.baseURL}`, personelDto);
  }

  updatePersonel(id: number, personelDto: PersonelDto): Observable<object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, PersonelDto);
  }

  deletePersonel(id: number): Observable<object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
