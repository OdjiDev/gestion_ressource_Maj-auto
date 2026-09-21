import { FactureDto } from './facture-dto';
import { ProduitDto } from './produit-dto';

export class LigneFactureDto {
  id = 0;
  quantite = 0;
  date = '';
  total = 0;

  produitDto: ProduitDto = new ProduitDto();
  factureDto: FactureDto = new FactureDto();
}
