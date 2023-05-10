import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {firstValueFrom} from "rxjs";
import {Enseignant, Miahoot} from "./interfaces";

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http : HttpClient) {}

  private urlServeurApi = '/api/enseignants';


  async getEnseignant(uid : String) : Promise<Enseignant> {
    console.log(firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${uid}`)))
    return firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${uid}/`));
  }

  async getMiahootsOfEnseignant(uid: string): Promise<Miahoot[]> {
    console.log('ici')
   // const enseignant = await this.getEnseignant(uid);
    console.log('la')

    const url = `${this.urlServeurApi}/${uid}/miahoots/`;
    console.log("quentin")
    return firstValueFrom(this.http.get<Miahoot[]>(url));
  }

}
