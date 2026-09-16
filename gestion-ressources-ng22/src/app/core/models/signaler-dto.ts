import { PersonelDto } from '..\@app/core/models/personel-dto';
import { ProduitDto } from '..\@app/core/models/produit-dto';
export class SignalerDto {

  id: number=0
  etat: string= "";
produitDto:ProduitDto= new ProduitDto();
personelDto:PersonelDto= new PersonelDto();
}
