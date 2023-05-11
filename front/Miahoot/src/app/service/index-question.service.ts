import { Injectable } from '@angular/core';
import { BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IndexQuestionService {
  private currentQuestionSubject = new BehaviorSubject<any>(null)
  public currentQuestion$ = this.currentQuestionSubject.asObservable();

  constructor() { }


  public setCurrentQuestion (index : any){
    this.currentQuestionSubject.next(index);
  }
}
