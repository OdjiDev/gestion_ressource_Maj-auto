export class CategorieDto {
  id!: number;
  code = '';
  nomcategorie = '';
  designation = '';
  // Add other   properties as needed
}

// Alias pour compatibilité
export type Categorie = CategorieDto;
