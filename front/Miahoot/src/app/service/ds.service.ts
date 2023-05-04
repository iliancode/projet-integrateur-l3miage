import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { lastValueFrom } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DsService {


  constructor(private http: HttpClient) {
  }

  async getEnseignant(email: string) {
    let url = "http://localhost:8080/api/enseignants/";

    let reponse = await lastValueFrom(this.http.get(url + email));
    console.log(reponse)
  }
  // getEnseignant(email : string) {


  /**
   *

  let update = await lastValueFrom(this.http.put<any>(this.url + "/2",this.body))
  console.log(update)

  let recup2 = await lastValueFrom(this.http.get<any>(this.url + "/2"))
  console.log(recup2)

   */
}
