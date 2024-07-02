
import { ProduitDto } from '../classes/produit-dto';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

 private baseURL= environment.baseURL+ "produits";

  constructor(private httpClient: HttpClient) { }

  getProduits(): Observable<ProduitDto[]>{
    return this.httpClient.get<ProduitDto[]>(`${this.baseURL}/list`);
  }

  getProduitById(id: number): Observable<ProduitDto>{
    return this.httpClient.get<ProduitDto>(`${this.baseURL}/${id}`);
  }

  addProduit(produitDto: ProduitDto): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, produitDto);
  }

  updateProduit(id: number, produitDto: ProduitDto): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, ProduitDto);
  }

  deleteProduit(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
