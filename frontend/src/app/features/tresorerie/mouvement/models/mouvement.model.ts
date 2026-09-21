import { CategorieMouvement, ModePaiement, StatutMouvement, TypeMouvement } from '../../shared/enums';

export interface Mouvement {
  id?: number;
  dateOperation: string;
  dateEnregistrement?: string;
  type: TypeMouvement;
  statut: StatutMouvement;
  montant: number;
  devise: string;
  motif: string;
  categorie: CategorieMouvement;
  modePaiement: ModePaiement;
  reference?: string;
  justificatifPath?: string;
  compteId: number;
  compteNom?: string;
  factureId?: number;
  parentId?: number;
  validatedBy?: string;
  validatedAt?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface MouvementCreateRequest {
  dateOperation: string;
  type: TypeMouvement;
  montant: number;
  devise?: string;
  motif: string;
  categorie: CategorieMouvement;
  modePaiement: ModePaiement;
  reference?: string;
  compteId: number;
  factureId?: number;
}

export interface SoldeDto {
  compteId: number;
  compteNom: string;
  soldeInitial: number;
  totalEntrees: number;
  totalSorties: number;
  soldeActuel: number;
  devise: string;
}
