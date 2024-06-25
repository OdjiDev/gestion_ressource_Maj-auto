import { FournisseurDto } from './fournisseur-dto';
export class FactureDto {
  id: number= 0;
 createdAt: string= "";
 numero: string= "";
 code:   string =  " ";
  datecommande: string= "";
 fournisseurDto: FournisseurDto= new FournisseurDto();
}
