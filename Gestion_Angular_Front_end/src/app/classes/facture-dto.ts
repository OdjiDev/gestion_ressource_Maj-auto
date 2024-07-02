import { LignedemandeDto } from './lignedemande-dto';
import { FournisseurDto } from './fournisseur-dto';
import { LigneFactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
<<<<<<< HEAD
  id: number = 0;
  createdAt: string = '';
  numero: string = '';
  code: string = ' ';
  total: number = 0;
  datecommande: string = '';
  fournisseurDto: FournisseurDto = new FournisseurDto();
  lignefactureDto: LignefactureDto = new LignefactureDto();
  produitDto: ProduitDto = new ProduitDto();
=======
  id: number= 0;
 createdAt: string= " ";
 numero: string= " ";
 code:   string =  " ";
 total: number= 0;
  datecommande: string= " ";
 fournisseurDto: FournisseurDto= new FournisseurDto();
 lignefactureDtos:LigneFactureDto= new LigneFactureDto();

>>>>>>> bc417733688935569d4905aaa6d89003519c4272
}
