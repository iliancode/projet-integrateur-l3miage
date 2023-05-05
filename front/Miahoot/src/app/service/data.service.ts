import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {firstValueFrom} from "rxjs";


interface Reponse {
  id?: string;
  label: string;
  estValide: boolean;
}

interface Question {
  id?: string;
  label: string;
  reponses: Reponse[];
}

interface Miahoot {
  id?: string;
  nom: string;
  questions: Question[];
}
interface Enseignant {
  pseudo : String,
  mail : String,
  mdp : String
}
@Injectable({
  providedIn: 'root'
})
export class DataService {

  private urlServeurApi = '/enseignants/';
  constructor(private http: HttpClient) { }

  // ENSEIGNANT

  async getEnseignant(mail : String) : Promise<Enseignant> {
    return firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${mail}`));
  }

  async createEnseignant(enseignant : Enseignant) : Promise<Enseignant> {
    return firstValueFrom(this.http.post<Enseignant>(`${this.urlServeurApi}`,enseignant));
  }


  // MIAHOOT

  async getMiahootsOfEnseignant(mail: string): Promise<Miahoot[]> {
    const enseignant = await this.getEnseignant(mail);
    const url = `${this.urlServeurApi}/${mail}/miahoots`;
    return firstValueFrom(this.http.get<Miahoot[]>(url));
  }

  // MIAHOOT - QUESTIONS

  // MIAHOOT - QUESTIONS - REPONSES

  // PARTIES

  // PARTICIPANT


}
