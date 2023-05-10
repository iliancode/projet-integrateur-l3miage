import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IndexQuestionService {
  private currentQuestionSubject = new BehaviorSubject<any>(null)
  public currentQuestion$ = this.currentQuestionSubject.asObservable();
  private listReponses = new BehaviorSubject<any>(null)
  public currentReponses = this.listReponses.asObservable();

  constructor() {

  }

  public setNewReponse (reponse : any){
    this.listReponses.next(reponse);
    //this.tab.push(reponse);
  }

  public setCurrentQuestion (index : any){
    this.currentQuestionSubject.next(index);
  }
}
