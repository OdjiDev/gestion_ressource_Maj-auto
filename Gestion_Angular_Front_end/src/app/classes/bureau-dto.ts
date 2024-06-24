import { PersonelDto } from 'src/app/classes/personel-dto';
import { DepartementDto } from './departement-dto';
export class BureauDto {

  id: number=0
  nom: string= "";
  departementDto: DepartementDto[]=[];
  //personelsDto: PersonelDto[]=[];

}
