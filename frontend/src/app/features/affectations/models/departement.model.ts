import { BureauDto } from './bureau.model';

export class DepartementDto {
  id: number = 0;
  nom: string = '';
  bureauxDto: BureauDto[] = [];
}
