import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { LignedemandeDto } from './lignedemande-dto';
import { LigneFactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
  id: number= 0;
 createdAt: string= " ";
 numero: string= " ";
 code:   string =  " ";
 total: number= 0;
  datecommande: string= " ";
 lignefactureDtos:LigneFactureDto[]=[];
 fournisseurDto:FournisseurDto=new FournisseurDto();

}
