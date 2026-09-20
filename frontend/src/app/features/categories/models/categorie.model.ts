export class CategorieDto {
  id!: number;
  code: string = '';
  nomcategorie: string = '';
  designation: string = '';
  // Add other   properties as needed
}

// Alias pour compatibilité
export type Categorie = CategorieDto;
