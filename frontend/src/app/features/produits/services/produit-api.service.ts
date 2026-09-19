
import { Produit } from '../models/produit.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

 private baseURL= environment.baseURL+ "produits";

  constructor(private httpClient: HttpClient) { }

  getProduits(): Observable<Produit[]>{
    return this.httpClient.get<Produit[]>(`${this.baseURL}/list`);
  }

  getProduitById(id: number): Observable<Produit>{
    return this.httpClient.get<Produit>(`${this.baseURL}/${id}`);
  }

  addProduit(produitDto: Produit): Observable<Produit>{
    return this.httpClient.post<Produit>(`${this.baseURL}`, produitDto);
  }

  updateProduit(id: number, produitDto: Produit): Observable<Produit>{
    return this.httpClient.put<Produit>(`${this.baseURL}/${id}`, produitDto);
  }

  deleteProduit(id: number): Observable<Produit>{
    return this.httpClient.delete<Produit>(`${this.baseURL}/${id}`);
  }


}
