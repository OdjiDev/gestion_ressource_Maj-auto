import { FactureDto } from './facture-dto';
import { ProduitDto } from "./produit-dto";

export class LignefactureDto {
  push(ligneItem: LignefactureDto) {
    throw new Error('Method not implemented.');
  }
  id: number= 0;
  quantite: number= 0;
  //date: string= "";
  total: number=0;
 produitDto: ProduitDto= new ProduitDto();
 factureDtoDto: FactureDto= new FactureDto();

}
