import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {DsService} from "../service/ds.service";
import {AuthService} from "../service/auth.service";
import {Auth} from "@angular/fire/auth";
import {Miahoot, Reponse} from "../service/interfaces";
import {PresentationService} from "../service/presentation.service";
import {Router} from "@angular/router";
import {firstValueFrom, Observable} from "rxjs";

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.scss']
})
export class CreationComponent implements OnInit{

  uid = '';
  miahoots: Miahoot[] = [];
  miahoot : Miahoot;
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



//création d'un nouveau miahoot
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
