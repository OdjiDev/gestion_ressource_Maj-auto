import { LignedemandeDto } from './lignedemande-dto';
import { FournisseurDto } from './fournisseur-dto';
import { LigneFactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
  id = 0;
  createdAt = '';
  numero = '';
  code = ' ';
  total = 0;
  datecommande = '';
  fournisseurDto: FournisseurDto = new FournisseurDto();
  lignefactureDto: LigneFactureDto = new LigneFactureDto();
  produitDto: ProduitDto = new ProduitDto();
}
