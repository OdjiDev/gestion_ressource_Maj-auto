
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { FactureDto } from './facture-dto';
import { ProduitDto } from "./produit-dto";

export class LigneFactureDto {

  id: number= 0;
  quantite: number= 0;
  //date: string= "";

  produitDto: ProduitDto= new ProduitDto();
 factureDto: FactureDto= new FactureDto();


}
