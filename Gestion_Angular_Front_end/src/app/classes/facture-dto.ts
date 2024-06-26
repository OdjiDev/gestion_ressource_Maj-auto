import { LignedemandeDto } from './lignedemande-dto';
import { FournisseurDto } from './fournisseur-dto';
import { LignefactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
  id: number= 0;
 createdAt: string= "";
 numero: string= "";
 code:   string =  " ";
 total: number= 0;
  datecommande: string= "";
 fournisseurDto: FournisseurDto= new FournisseurDto();
 lignefactureDto:LignefactureDto= new LignefactureDto();
 produitDto:ProduitDto= new ProduitDto();
}
