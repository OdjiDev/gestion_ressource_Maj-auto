import { ProduitDto } from './produit-dto';
import { SocieteDto } from './societe-dto';

export class ContratDto {
  id = 0;
  code = '';
  nom = '';
  datedebut = '';
  datedefin = '';
  motif = '';
  societeDto: SocieteDto = new SocieteDto();
}
