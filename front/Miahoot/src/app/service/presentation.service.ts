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
export class PresentationService {

  constructor(private http : HttpClient) {}

  private urlServeurApi = '/enseignants/';


  async getEnseignant(mail : String) : Promise<Enseignant> {
    return firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${mail}`));
  }

  async getMiahootsOfEnseignant(mail: string): Promise<Miahoot[]> {
    const enseignant = await this.getEnseignant(mail);
    const url = `${this.urlServeurApi}/${mail}/miahoots`;
    return firstValueFrom(this.http.get<Miahoot[]>(url));
  }

}
