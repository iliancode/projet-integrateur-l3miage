import { ChangeDetectionStrategy, Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Miahoot} from "../service/interfaces";
import { DsService } from "../service/ds.service";
@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent {

  constructor(public router : Router, public ds:DsService) {
  }



  // supprime dans la vue html et dans l'api
  supprimeMiahoot(id : number) {
    this.ds.deleteMiahoot(id)
      .then(()=>{
        console.log("supprimé de la bd api")
        //get by id à modifier
        const del = document.getElementById("" + id)
        if(del !== null){
          del.remove()
        }else{
          console.warn("l element n existe pas dans l html")
        }
      })
  }

  postMiahoot(id: number, miahoot: string) {
    let jsonmiahoot = JSON.parse(miahoot);
    //json to Miahoot object
    let miahootObj: Miahoot = {
      nom: jsonmiahoot.nom,
      questions: jsonmiahoot.questions
    }


    this.ds.postM(id, miahootObj);
  }




}
