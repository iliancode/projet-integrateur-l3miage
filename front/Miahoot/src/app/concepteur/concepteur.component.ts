import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Miahoot} from "../service/interfaces";
import {DsService, Enseignant} from "../service/ds.service";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "../service/auth.service";
import {firstValueFrom, Observable} from "rxjs";
import {PresentationService} from "../service/presentation.service";
@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent implements OnInit{
  uid = '';
  miahoots: Miahoot[] = [];
  miahoot : Miahoot;
private email : string | null | undefined ;

  constructor(public router : Router, public ds:DsService, public auth :AuthService, public ps:PresentationService) {
    this.miahoot = this.miahoots[0];

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
     this.uid = user?.uid ?? '';
    }) ;
  }



  ngOnInit(): void {
    const u =  firstValueFrom(this.auth.currentUser).then(user=>{

      this.ps.getMiahootsOfEnseignant(user?.uid??'vache')
        .then(miahoots => {
          this.miahoots = miahoots;
          this.miahoot = this.miahoots[0];
          console.log(this.miahoots);
          console.log(this.miahoot);

        });

    }) ;
  }
  // supprime dans la vue html et dans l'api


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

  postMiahoot(uid: String, miahoot: string) {
    let jsonmiahoot = JSON.parse(miahoot);
    //json to Miahoot object
    let miahootObj: Miahoot = {
      nom: jsonmiahoot.nom,
      questions: jsonmiahoot.questions
    }


    this.ds.postM(uid, miahootObj);
  }




}
