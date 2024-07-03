import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { ProduitDto } from './produit-dto';
export class FactureDto {
  id: number = 0;
  createdAt: string = '';
  numero: string = '';
  code: string = ' ';
  total: number = 0;
  datecommande: string = '';
  fournisseurDto: FournisseurDto = new FournisseurDto();
  produitDto: ProduitDto = new ProduitDto();
}
