import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SelectedMiahootService{
  private selectedMiahootIdSubject = new BehaviorSubject<number>(0);
  public selectedMiahootId$ = this.selectedMiahootIdSubject.asObservable();

  setSelectedMiahootId(id : number){
    this.selectedMiahootIdSubject.next(id);
  }

}
