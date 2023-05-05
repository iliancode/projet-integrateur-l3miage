import { Injectable } from '@angular/core';
import {BehaviorSubject, combineLatest, firstValueFrom, lastValueFrom, Observable, switchMap} from 'rxjs';
import { AuthService } from './auth.service';
import { Miahoot, Enseignant } from '../service/interfaces'

// Le service d'accès aux données du professeur en tant que concepteur
import { HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class DsService {
  private bsAskUpdate = new BehaviorSubject<void>(undefined);
  readonly obsMiahoots: Observable<Miahoot[]>;

  constructor(private http: HttpClient, private auth: AuthService) {

    this.obsMiahoots = combineLatest([this.bsAskUpdate, auth.currentUser]).pipe(
      switchMap( async ([_, U]) => {
        if (U === null) {
          return []
        }
        const miahoots = await firstValueFrom( http.get<Miahoot[]>( `/api/enseignants/${U.uid}/miahoots}` ) )
        return miahoots;
      })
    )
  }

  async createMiahoot(M: Miahoot): Promise<Miahoot> {
    const U = await firstValueFrom(this.auth.currentUser);
    if (U !== null) {
      this.http.post<Miahoot>(`/api/enseignants/${U.uid}/miahoots`, M);
      this.bsAskUpdate.next();
    }
    throw "illegal Miahoot creation";
  }


  async getEnseignant(mail: String): Promise<Enseignant> {
    const U = await firstValueFrom(this.auth.currentUser);
    if (U !== null) {
      return  firstValueFrom(this.http.get<Enseignant>(`/api/enseignants/${U.uid}/miahoots`));
    }
    throw "enseignant introuvable"

  }

  async get(endpoint: string, recherche: string) {
    let url = "http://localhost:8080/api/";

    let reponse = await lastValueFrom(this.http.get<any>(url + endpoint +"/"  +recherche));
    console.log(reponse)
  }

  //post
   post(endpoint: string, body: Enseignant) {
    let url = "http://localhost:8080/api/";
     return firstValueFrom(this.http.post<any>(url + endpoint, body));
   }

  async deleteMiahoot(idMiahoot:  number){
    const U = await firstValueFrom(this.auth.currentUser);
    if(U !== null){
      this.http.delete<Miahoot>(`/api/enseignants/${U.uid}/miahoots/${idMiahoot}`)
    }
    throw "erreur lors de la suppression du Miahoot"
  }


  async postE( enseignant: Enseignant){
    let url = "http://localhost:8080/api/enseignants/"
    let reponse =  await lastValueFrom(this.http.post(url, enseignant));
    console.log(reponse)
  }

}
