// ==================== DOMAIN MODEL ====================

export interface Produit {
  id?: number;
  codeproduit: string;
  nom: string;
  designation?: string;
  quantite: number;
  prixAchat?: number;
  categorieId?: number;
  magasinId?: number;
  categorieNom?: string;
  magasinNom?: string;
  createdAt?: string;
  updatedAt?: string;
}

// ==================== SUMMARY ====================

export interface ProduitSummary {
  id: number;
  codeproduit: string;
  nom: string;
  quantite: number;
  categorieNom?: string;
}

// ==================== FILTER ====================

export interface ProduitFilter {
  nom?: string;
  categorieId?: number;
  magasinId?: number;
}
