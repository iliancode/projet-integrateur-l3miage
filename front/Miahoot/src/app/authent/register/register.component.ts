import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { DsService} from "../../service/ds.service";
import {timeout} from "rxjs";
import { Enseignant } from 'src/app/service/ds.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email : string = '';
  password : string = '';

  constructor(private auth : AuthService, private ds :DsService) { }

  ngOnInit(): void {
  }

  register() {

    if(this.email == '') {
      alert('Please enter email');
      return;
    }

    if(this.password == '') {
      alert('Please enter password');
      return;
    }
    this.newEnseignant(this.email, "zwi", this.password)
    console.log("la")
    timeout(5000);
    this.auth.register(this.email,this.password);

    this.email = '';
    this.password = '';

  }

  newEnseignant(mail:string, pseudo:string, mdp:string) {
    this.ds.post("enseignants", {mail:mail, pseudo:pseudo, mdp:mdp})
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
    console.log("ici")
  }
  wawawa() {

    let x: Enseignant = {  mail:"oui@gmail.com",pseudo:"ni", mdp:"na"}
    this.ds.postE( x);
    console.log(x)
  }
}
