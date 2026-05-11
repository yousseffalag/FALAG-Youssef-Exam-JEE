export interface LoginResponse {
  'access-token': string;
  'token-type': string;
  scope: string;
}

export interface Client {
  id?: number;
  nom: string;
  email: string;
}

export interface Contrat {
  id?: number;
  clientId: number;
  dateSouscription: string;
  statut: 'EN_COURS' | 'VALIDE' | 'RESILIE';
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
}

export interface AssuranceAuto extends Contrat {
  numImmatriculation: string;
  marque: string;
  modele: string;
}

export interface Paiement {
  id?: number;
  contratId: number;
  date: string;
  montant: number;
  type: 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';
}
