import { ReparerDto } from './reparer-dto';
import { FacturereparerDto } from './facturereparer-dto';
import { ProduitDto } from './produit-dto';

export class LignefacturereparerDto {
  id = 0;
  quantite = 0;
  date = '';
  produitDto: ProduitDto = new ProduitDto();
  reparerDto: ReparerDto = new ReparerDto();
  facturereparerDto: FacturereparerDto = new FacturereparerDto();
}
