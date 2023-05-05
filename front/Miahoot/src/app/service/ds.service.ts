import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { BehaviorSubject, Observable, combineLatest, firstValueFrom, lastValueFrom, switchMap } from 'rxjs';
import { AuthService } from './auth.service';
import { Miahoot, Enseignant } from './interfaces'

// Le service d'accès aux données du professeur en tant que concepteur
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

  async deleteMiahoot(idMiahoot:  number){
    const U = await firstValueFrom(this.auth.currentUser);
    if(U !== null){
      this.http.delete<Miahoot>(`/api/enseignants/${U.uid}/miahoots/${idMiahoot}`)
    }
    throw "erreur lors de la suppression du Miahoot"
  }



}
