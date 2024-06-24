import { FournisseurDto } from './fournisseur-dto';
export class FactureDto {
  id: number= 0;
  code: string= "";
  datecommande: string= "";
 fournisseurDto: FournisseurDto= new FournisseurDto();
}
