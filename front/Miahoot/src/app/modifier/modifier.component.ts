import {Component, OnInit} from '@angular/core';
import {DsService} from "../service/ds.service";
import {Miahoot} from "../service/interfaces";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {firstValueFrom} from "rxjs";
import {User} from "@angular/fire/auth";
import {SelectedMiahootService} from "../service/selected-miahoot.service";

@Component({
  selector: 'app-modifier',
  templateUrl: './modifier.component.html',
  styleUrls: ['./modifier.component.scss']
})
export class ModifierComponent implements OnInit{
  userId : string = ""
  miahoot! : Miahoot
  miahootEdit = ""
  miahootId! : number
  constructor(private sms: SelectedMiahootService,private ds : DsService, private router : Router, private auth : AuthService) {
  }

  ngOnInit() {
    //ici on recup l'id courant du miahoot et l'utilisateur courant
    const u =  firstValueFrom(this.auth.currentUser)
      .then(( u )=> {
        this.userId =  u?.uid?? "undifined"
        //recup id miahoot ----------------
        this.sms.selectedMiahootId$.subscribe( (id) => {
          this.miahootId = id
          this.ds.getMiahootById(this.userId, this.miahootId).then((m)=>{
            this.miahoot = m
            console.log(this.miahoot)
            this.miahootEdit = this.afficheMiahootJson(this.miahoot)
            console.log(this.miahootEdit)
          })
        })
      })
      .catch(()=>console.warn("ERREUR RECUP UID "))
  }


  majMiahoot(idMiahoot: number, maj: string){
    console.log("valeur recup du textarea : " + maj)
    const miahootSent = this.ds.jsonToMiahoot(maj)
    console.log("valeur utilisée pour l'update : " + miahootSent)
    this.ds.updateMiahoot(idMiahoot,miahootSent)
      .then(()=>{
        console.log("miahoot updated")
        //on redirige vers la page concepteur
        this.router.navigateByUrl("/concepteur")
      })
      .catch(()=>{
        console.warn("erreur lors de la mise a jour du miahoot ")
      })
  }

  afficheMiahootJson(miahootToJson: Miahoot){
    return JSON.stringify(miahootToJson)
  }



}
