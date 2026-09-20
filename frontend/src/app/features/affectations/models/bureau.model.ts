import { DepartementDto } from './departement.model';
import { PersonelDto } from '@features/users/models/personel.model';

export class BureauDto {
  id = 0;
  nom = '';
  departementDto: DepartementDto | null = null;
  personelsDto: PersonelDto[] = [];
}
