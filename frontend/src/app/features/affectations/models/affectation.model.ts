import { PersonelDto } from '@features/users/models/personel.model';
import { Produit } from '@features/produits/models/produit.model';

export class AffectationDto {
  id = 0;
  personelDto: PersonelDto | null = null;
  produit: Produit | null = null;
  dateAffectation = '';
  quantite = 0;
}
