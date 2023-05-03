import { Component } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.scss']
})
export class ParticipantComponent {

  pseudo = ""

  constructor(public as: AuthService, private router: Router) {
  }
  // a modifier car il faut voir comment gerer le pseudo ...
  signInAnonymously(){
      this.as.anonymousLogin()
  }
  //pour le deplacement vers la partie
  afterSignIn(){
    this.router.navigateByUrl('presentation')
  }

}
