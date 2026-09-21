export interface DashboardStats {
  totalProduits: number;
  totalCategories: number;
  totalFournisseurs: number;
  totalFactures: number;
  produitsEnRupture: number;
  produitsSousSeuil: number;
  valeurStock: number;
  totalPersonels: number;
}

export interface AlertProduit {
  id: number;
  nom: string;
  codeproduit: string;
  quantite: number;
  seuilAlerte?: number;
}

export interface RecentFacture {
  id: number;
  numero: string;
  datecommande: string;
  fournisseurNom?: string;
  total?: number;
}
