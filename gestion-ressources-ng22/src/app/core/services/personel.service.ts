import { Injectable } from '@angular/core';
import { PersonelDto } from '../classes/personel-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class PersonelService {

 private baseURL= environment.baseURL+ "personels";

  constructor(private httpClient: HttpClient) { }

  getPersonels(): Observable<PersonelDto[]>{
    return this.httpClient.get<PersonelDto[]>(`${this.baseURL}/list`);
  }

  getPersonelById(id: number): Observable<PersonelDto>{
    return this.httpClient.get<PersonelDto>(`${this.baseURL}/${id}`);
  }

  addPersonel(personelDto: PersonelDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, personelDto);
  }


  updatePersonel(id: number, personelDto: PersonelDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, PersonelDto);
  }

  deletePersonel(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
