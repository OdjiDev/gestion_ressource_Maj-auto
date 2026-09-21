import { ProduitDto } from './produit-dto';
import { ReparerDto } from './reparer-dto';

export class LignereparationDto {
  id = 0;
  quantite = 0;
  date = '';
  produitDto: ProduitDto = new ProduitDto();
  reparerDto: ReparerDto = new ReparerDto();
}
