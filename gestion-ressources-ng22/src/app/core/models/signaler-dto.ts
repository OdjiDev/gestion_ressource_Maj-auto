import { PersonelDto } from '..\./personel-dto';
import { ProduitDto } from '..\./produit-dto';
export class SignalerDto {

  id: number=0
  etat: string= "";
produitDto:ProduitDto= new ProduitDto();
personelDto:PersonelDto= new PersonelDto();
}
