import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Miahoot} from "../service/interfaces";
import {DsService, Enseignant} from "../service/ds.service";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "../service/auth.service";
import {firstValueFrom, Observable} from "rxjs";
import {PresentationService} from "../service/presentation.service";
import {SelectedMiahootService} from "../service/selected-miahoot.service";
@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent implements OnInit{
private email : string | null | undefined ;

  uid = '';
  miahoots: Miahoot[] = [];
  miahoot : Miahoot;
  idMiahootCourant! : number
  constructor(public router : Router, public ds:DsService, public auth :AuthService, public ps:PresentationService, private sms: SelectedMiahootService) {
    this.miahoot = this.miahoots[0];

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
     this.uid = user?.uid ?? '';
    }) ;
  }

  selectMiahoot(id: number){
    const vraiId = this.gestionIdMiahoot(id)?? 0
    this.sms.setSelectedMiahootId(vraiId);
    console.log("id miahoot : "+ vraiId);
    this.router.navigateByUrl("/modifier")
  }


  ngOnInit(): void {
    const u =  firstValueFrom(this.auth.currentUser).then(user=>{

      this.ps.getMiahootsOfEnseignant(user?.uid??'vache')
        .then(miahoots => {
          this.miahoots = miahoots;
          this.miahoot = this.miahoots[0];
          console.log(this.miahoots);
          console.log(this.miahoot);

        })
        .catch(()=>console.warn("probleme avec le get des miahoots"))

    })
      .catch(()=>console.warn("probleme avec la recup de l' utilisateur "))
  }


  // supprime dans la vue html et dans l'api
  supprimeMiahoot(id : number) {
    const vraiId = this.gestionIdMiahoot(id-1)?? 0
    this.ds.deleteMiahoot(vraiId)
      .then(()=>{
        console.log("supprimé de la bd api")
        /*const del = document.getElementById("" + id)
        if(del !== null){
          del.remove()
        }else{
          console.warn("l element n existe pas dans l html")
        }*/
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

  // renvoi le "vrai" id du miahoot
  gestionIdMiahoot(idAffichage : number){
    return this.miahoots[idAffichage].id
  }




}
