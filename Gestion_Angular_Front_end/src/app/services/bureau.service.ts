
import { BureauDto } from '../classes/bureau-dto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BureauService {

 private baseURL= environment.baseURL+ "bureaus";

  constructor(private httpClient: HttpClient) { }

  getBureaus(): Observable<BureauDto[]>{
    return this.httpClient.get<BureauDto[]>(`${this.baseURL}/list`);
  }

  getBureauById(id: number): Observable<BureauDto>{
    return this.httpClient.get<BureauDto>(`${this.baseURL}/${id}`);
  }


  addBureau(bureauDto: BureauDto): Observable<BureauDto>{
    return this.httpClient.post<BureauDto>(`${this.baseURL}`, bureauDto);
  }

<<<<<<< HEAD
  // updateBureau(id: number, bureauDto: BureauDto): Observable<BureauDto>{
  //   return this.httpClient.put(`${this.baseURL}/${id}`, BureauDto);
  // }


  updateBureau(id: number, bureau: BureauDto): Observable<BureauDto> {
    return this.httpClient.put<BureauDto>(`${this.baseURL}/${id}`, bureau);
  }
  deleteBureau(id: number): Observable<BureauDto>{
    return this.httpClient.delete<BureauDto>(`${this.baseURL}/${id}`);
=======
  updateBureau(id: number, bureauDto: BureauDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, BureauDto);
  }

  deleteBureau(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
>>>>>>> parent of b667cef (Modification des formulaire add vers le form group et ajout de filtre a quelque cas)
  }


}
