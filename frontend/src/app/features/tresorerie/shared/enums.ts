export type TypeCompte = 'CAISSE' | 'BANQUE' | 'MOBILE_MONEY';
export const TYPE_COMPTE_OPTIONS: { value: TypeCompte; label: string }[] = [
  { value: 'CAISSE', label: 'Caisse' },
  { value: 'BANQUE', label: 'Banque' },
  { value: 'MOBILE_MONEY', label: 'Mobile Money' }
];

export type TypeMouvement = 'ENTREE' | 'SORTIE';
export const TYPE_MOUVEMENT_OPTIONS: { value: TypeMouvement; label: string }[] = [
  { value: 'ENTREE', label: 'Entrée' },
  { value: 'SORTIE', label: 'Sortie' }
];

export type StatutMouvement = 'BROUILLON' | 'VALIDE' | 'ANNULE';

export type ModePaiement = 'ESPECES' | 'VIREMENT' | 'CHEQUE' | 'MOBILE_MONEY';
export const MODE_PAIEMENT_OPTIONS: { value: ModePaiement; label: string }[] = [
  { value: 'ESPECES', label: 'Espèces' },
  { value: 'VIREMENT', label: 'Virement' },
  { value: 'CHEQUE', label: 'Chèque' },
  { value: 'MOBILE_MONEY', label: 'Mobile Money' }
];

export type CategorieMouvement = 'VENTE' | 'ACHAT' | 'SALAIRE' | 'LOYER' | 'TRANSPORT' | 'ELECTRICITE' | 'EAU' | 'INTERNET' | 'TAXE' | 'AUTRE';
export const CATEGORIE_MOUVEMENT_OPTIONS: { value: CategorieMouvement; label: string }[] = [
  { value: 'VENTE', label: 'Vente' },
  { value: 'ACHAT', label: 'Achat' },
  { value: 'SALAIRE', label: 'Salaire' },
  { value: 'LOYER', label: 'Loyer' },
  { value: 'TRANSPORT', label: 'Transport' },
  { value: 'ELECTRICITE', label: 'Électricité' },
  { value: 'EAU', label: 'Eau' },
  { value: 'INTERNET', label: 'Internet' },
  { value: 'TAXE', label: 'Taxe' },
  { value: 'AUTRE', label: 'Autre' }
];
