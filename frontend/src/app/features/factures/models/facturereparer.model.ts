import { SocieteDto } from './societe-dto';

export class FacturereparerDto {
  id = 0;
  code = '';
  date = '';
  societeDto: SocieteDto = new SocieteDto();
}
