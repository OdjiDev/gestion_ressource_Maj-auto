import { RoleDto } from "./role-dto";

export class UsersDto {
  userIdDto: string="";
  passwordDto:string="";

  roleDto:RoleDto= new RoleDto();
  repeatpasswordDto :string="";
}
