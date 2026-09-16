import { PersonelDto } from '..\../core/models/personel-dto';
import { ProduitDto } from '..\../core/models/produit-dto';
export class SignalerDto {

  id: number=0
  etat: string= "";
produitDto:ProduitDto= new ProduitDto();
personelDto:PersonelDto= new PersonelDto();
}
