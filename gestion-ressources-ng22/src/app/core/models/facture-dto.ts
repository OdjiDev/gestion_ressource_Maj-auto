import { LignedemandeDto } from './lignedemande-dto';
import { FournisseurDto } from './fournisseur-dto';
import { LigneFactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {

  id: number = 0;
  createdAt: string = '';
  numero: string = '';
  code: string = ' ';
  total: number = 0;
  datecommande: string = '';
  fournisseurDto: FournisseurDto = new FournisseurDto();
  lignefactureDto: LigneFactureDto = new LigneFactureDto();
  produitDto: ProduitDto = new ProduitDto();

}
