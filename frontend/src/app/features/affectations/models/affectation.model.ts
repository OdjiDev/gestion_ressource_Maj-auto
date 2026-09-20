import { PersonelDto } from '@features/users/models/personel.model';
import { Produit } from '@features/produits/models/produit.model';

export class AffectationDto {
  id: number = 0;
  personelDto: PersonelDto | null = null;
  produit: Produit | null = null;
  dateAffectation: string = '';
  quantite: number = 0;
}
