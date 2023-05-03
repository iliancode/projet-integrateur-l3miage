import { ChangeDetectionStrategy, Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent {

  constructor(public router : Router) {
  }


  // supprime dans la vue html et dans l'api
  deleteMiahoot(id : string) {
    const del = document.getElementById(id)
    if(del !== null){
      del.remove()
    }

  }

}
