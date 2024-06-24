import { FactureDto } from './facture-dto';
import { ProduitDto } from "./produit-dto";

export class LignefactureDto {
  id: number= 0;
  quantite: number= 0;
  date: string= "";
 produitDto: ProduitDto= new ProduitDto();
 factureDtoDto: FactureDto= new FactureDto();

}
