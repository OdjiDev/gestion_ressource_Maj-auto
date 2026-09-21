import { PersonelDto } from '.././personel-dto';
import { BureauDto } from '.././bureau-dto';

export class DemandeDto {
  id = 0;
  motif = '';

  bureauDto: BureauDto = new BureauDto();
  personelDto: PersonelDto = new PersonelDto();
}
