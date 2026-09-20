import { BureauDto } from './bureau.model';

export class DepartementDto {
  id = 0;
  nom = '';
  bureauxDto: BureauDto[] = [];
}
