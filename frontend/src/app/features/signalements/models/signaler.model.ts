import { PersonelDto } from '.././personel-dto';
import { ProduitDto } from '.././produit-dto';
export class SignalerDto {
  id = 0;
  etat = '';
  produitDto: ProduitDto = new ProduitDto();
  personelDto: PersonelDto = new PersonelDto();
}
