import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { DsService} from "../../service/ds.service";
import { Enseignant } from 'src/app/service/interfaces';
import {firstValueFrom, timeout} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email : string = '';
  password : string = '';
  uid : string |undefined = '';
  constructor(private auth : AuthService, private ds :DsService) { }

  ngOnInit(): void {
    const u = firstValueFrom(this.auth.currentUser).then(user => console.log(user?.uid));

  }

  register() {

    if (this.email == '') {
      alert('Please enter email');
      return;
    }

    if (this.password == '') {
      alert('Please enter password');
      return;
    }
    let pseudo = "zwi"
    //uid = uid of the firebase user


    this.auth.register(this.email, this.password);
    timeout(3000)

    let y: Enseignant = {uid:this.uid ,mail: this.email, pseudo: pseudo, mdp: this.password};
    console.log("uid : " + y.uid);
    console.log("mail : " + y.mail);
    console.log("pseudo : " + y.pseudo);
    console.log("mdp : " + y.mdp);
    this.newEnseignant(y);


    console.log("la")
    this.email = '';
    this.password = '';
  }
    newEnseignant(e: Enseignant) {
      this.ds.postE(e)
        .then(enseignant => console.log(enseignant))
        .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
      console.log("ici")
    }
    wawawa() {

      let x: Enseignant = {  uid:this.uid, mail:"oui@gmail.com",pseudo:"ni", mdp:"na"}
      this.ds.postE( x);
      console.log(x)
    }
  }
