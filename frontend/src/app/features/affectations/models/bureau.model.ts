import { DepartementDto } from './departement.model';
import { PersonelDto } from '@features/users/models/personel.model';

export class BureauDto {
  id: number = 0;
  nom: string = '';
  departementDto: DepartementDto | null = null;
  personelsDto: PersonelDto[] = [];
}
