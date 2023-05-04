import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from "@angular/common/http";
import {firstValueFrom, lastValueFrom, Observable} from 'rxjs';

export interface Enseignant {
    pseudo: string;
    mail: string;
    mdp: string;
}

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

@Injectable({
  providedIn: 'root'
})
export class DsService {



  constructor(private http: HttpClient) {
  }


  //get
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

  async postE( enseignant: Enseignant){
    let url = "http://localhost:8080/api/enseignants/"
    let reponse =  await lastValueFrom(this.http.post(url, enseignant));
    console.log(reponse)
  }

  async postM( mail:String,  miahoot: Miahoot){
    let url = "http://localhost:8080/api/enseignants/"
    url += mail + "/miahoot"
    let reponse =  await lastValueFrom(this.http.post(url, miahoot));
    console.log(reponse)
  }


}

export class Mihaoot {
}
