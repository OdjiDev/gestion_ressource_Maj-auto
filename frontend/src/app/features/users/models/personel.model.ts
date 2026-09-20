import { RoleDto } from '@features/auth/models/role.model';

export class PersonelDto {
  id = 0;
  username = '';
  numero = '';
  email = '';
  password = '';
  roleDto: RoleDto | null = null;
}
