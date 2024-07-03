<<<<<<< HEAD
import { LignedemandeDto } from './lignedemande-dto';
import { FournisseurDto } from './fournisseur-dto';
import { LigneFactureDto } from './lignefacture-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {

=======
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547
  id: number = 0;
  createdAt: string = '';
  numero: string = '';
  code: string = ' ';
  total: number = 0;
  datecommande: string = '';
  fournisseurDto: FournisseurDto = new FournisseurDto();
<<<<<<< HEAD
  lignefactureDto: LigneFactureDto = new LigneFactureDto();
  produitDto: ProduitDto = new ProduitDto();

=======
  produitDto: ProduitDto = new ProduitDto();
>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547
}
