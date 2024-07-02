
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { FactureDto } from './facture-dto';
import { ProduitDto } from "./produit-dto";

export class LigneFactureDto {

  id: number= 0;
  quantite: number= 0;
  //date: string= "";
<<<<<<< HEAD
  total: number=0;
 produitDto: ProduitDto= new ProduitDto();
 factureDtoDto: FactureDto= new FactureDto();
=======
 
  produitDto: ProduitDto= new ProduitDto();
  factureDto: FactureDto= new FactureDto();

>>>>>>> bc417733688935569d4905aaa6d89003519c4272

}
