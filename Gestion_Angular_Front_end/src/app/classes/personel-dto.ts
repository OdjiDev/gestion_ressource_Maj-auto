export class PersonelDto {
  formatDate(arg0: Date): Date {
    throw new Error("Method not implemented.");
  }
  id: number= 0;
    nom: string= "";
    prenom: string= "";
    dateDeNaissance: string='';
    sexe: string= "";
    lieuDeNaissance: string= "";
    namePhoto: any;
    urlPhoto: any;
    username: any;
    numero: number= 0;
    email: string= "";
    password: string= "";
    roles: String[]= [];
}
