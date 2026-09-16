import { DepartementDto } from '..\./departement-dto';
import { PersonelDto } from '..\./personel-dto';

export class BureauDto {

  id: number=0
  nom: string= "";
  departementDto:DepartementDto= new DepartementDto();
  //personelsDto: PersonelDto[]=[];

}
