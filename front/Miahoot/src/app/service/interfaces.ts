export interface Reponse {
  id?: number;
  label: string;
  estValide: boolean;
}

export interface Question {
  id?: number;
  label: string;
  reponses: Reponse[];
}

export interface Miahoot {
  id?: number;
  nom: string;
  questions: Question[];
}


export interface Enseignant {
  uid: string|undefined;
  mail: string;
  mdp: string;
  pseudo: string;
  miahoots?: Miahoot[];
  parties?: Partie[];
}

export interface Partie{
  codePartie: number;
  nom: string;
  miahoot: Miahoot;
}

export interface Participant{
  id?: number;
  pseudo : string;
  partie : Partie;
}
