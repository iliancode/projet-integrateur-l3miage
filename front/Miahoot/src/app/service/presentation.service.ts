import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {firstValueFrom} from "rxjs";
import { Enseignant, Miahoot } from './interfaces';


@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http : HttpClient) {}

  private urlServeurApi = '/api/enseignants';


  async getEnseignant(id : number) : Promise<Enseignant> {
    return firstValueFrom(this.http.get<Enseignant>(`${this.urlServeurApi}/${id}`));
  }

  async getMiahootsOfEnseignant(id: number): Promise<Miahoot[]> {
    const enseignant = await this.getEnseignant(id);
    const url = `${this.urlServeurApi}/${id}/miahoots`;
    return firstValueFrom(this.http.get<Miahoot[]>(url));
  }

}
