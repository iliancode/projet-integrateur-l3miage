import { ChangeDetectionStrategy, Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DsService} from "../service/ds.service";

@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent {

  constructor(public ds: DsService, public router : Router) {
  }


  // supprime dans la vue html et dans l'api
  supprimeMiahoot(id : number) {
      this.ds.deleteMiahoot(id)
        .then(()=>{
          console.log("supprimé de la bd api")
          const del = document.getElementById("" + id)
          if(del !== null){
            del.remove()
          }else{
            console.warn("l element n existe pas dans l html")
          }
        })
  }

}
