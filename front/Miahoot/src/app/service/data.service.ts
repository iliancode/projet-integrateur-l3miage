import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {firstValueFrom} from "rxjs";
import { Enseignant, Miahoot } from './interfaces';



@Injectable({
  providedIn: 'root'
})
export class DataService {

  private urlServeurApi = '/enseignants/';
  constructor(private http: HttpClient) { }

  // ENSEIGNANT

  async getEnseignant(id : number) : Promise<Enseignant> {
    return firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${id}`));
  }

  async createEnseignant(enseignant : Enseignant) : Promise<Enseignant> {
    return firstValueFrom(this.http.post<Enseignant>(`${this.urlServeurApi}`,enseignant));
  }


  // MIAHOOT

  async getMiahootsOfEnseignant(id: number): Promise<Miahoot[]> {
    const enseignant = await this.getEnseignant(id);
    const url = `${this.urlServeurApi}/${id}/miahoots`;
    return firstValueFrom(this.http.get<Miahoot[]>(url));
  }

  // MIAHOOT - QUESTIONS

  // MIAHOOT - QUESTIONS - REPONSES

  // PARTIES

  // PARTICIPANT


}
