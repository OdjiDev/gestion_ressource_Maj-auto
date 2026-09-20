import { TypeCompte } from '../../shared/enums';

export interface Compte {
  id?: number;
  code: string;
  nom: string;
  type: TypeCompte;
  soldeInitial: number;
  devise: string;
  seuilAlerte?: number | null;
  actif: boolean;
  soldeActuel?: number;
  createdAt?: string;
  updatedAt?: string;
}

export interface CompteCreateRequest {
  code: string;
  nom: string;
  type: TypeCompte;
  soldeInitial: number;
  devise: string;
  seuilAlerte?: number | null;
  actif: boolean;
}
