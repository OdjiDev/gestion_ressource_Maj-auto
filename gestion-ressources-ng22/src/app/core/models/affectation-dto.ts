import { PersonelDto } from '..\@app/core/models/personel-dto';
import { ProduitDto } from "./produit-dto";

export class AffectationDto {
  id: number= 0;
  quantite: string= "";
   date: string="";
   motif: string="";
   produitDto: ProduitDto= new ProduitDto();
   personelDto: PersonelDto= new PersonelDto();
   
}

