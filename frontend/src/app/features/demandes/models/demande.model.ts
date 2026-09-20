import { PersonelDto } from '.././personel-dto';
import { BureauDto } from '.././bureau-dto';

export class DemandeDto {
  id: number = 0;
  motif: string = '';

  bureauDto: BureauDto = new BureauDto();
  personelDto: PersonelDto = new PersonelDto();
}
