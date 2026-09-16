import { PersonelDto } from '..\@app/core/models/personel-dto';
import { BureauDto } from '..\@app/core/models/bureau-dto';

export class DemandeDto {
  id: number= 0
  motif: string= "";

bureauDto: BureauDto= new BureauDto();
personelDto: PersonelDto= new PersonelDto();
}
