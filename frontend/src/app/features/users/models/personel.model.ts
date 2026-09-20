import { RoleDto } from '@features/auth/models/role.model';

export class PersonelDto {
  id: number = 0;
  username: string = '';
  numero: string = '';
  email: string = '';
  password: string = '';
  roleDto: RoleDto | null = null;
}
