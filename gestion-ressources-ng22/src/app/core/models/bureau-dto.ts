import { DepartementDto } from '..\../core/models/departement-dto';
import { PersonelDto } from '..\../core/models/personel-dto';

export class BureauDto {

  id: number=0
  nom: string= "";
  departementDto:DepartementDto= new DepartementDto();
  //personelsDto: PersonelDto[]=[];

}
