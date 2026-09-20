import { ProduitDto } from './produit-dto';

export class AvarieDto {
  id = 0;
  quantite = '';
  nom = '';
  date = '';
  motif = '';
  produitDto: ProduitDto = new ProduitDto();
}
