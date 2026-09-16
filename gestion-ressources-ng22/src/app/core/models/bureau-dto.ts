import { DepartementDto } from '..\@app/core/models/departement-dto';
import { PersonelDto } from '..\@app/core/models/personel-dto';

export class BureauDto {

  id: number=0
  nom: string= "";
  departementDto:DepartementDto= new DepartementDto();
  //personelsDto: PersonelDto[]=[];

}
