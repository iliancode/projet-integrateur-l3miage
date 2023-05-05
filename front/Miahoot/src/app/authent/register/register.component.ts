import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { DsService} from "../../service/ds.service";
import { Enseignant } from 'src/app/service/interfaces';

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
    let pseudo = "zwi"
    let y : Enseignant = {mail:this.email, pseudo:pseudo, mdp:this.password};
    this.newEnseignant(y);
    console.log("la")
    this.auth.register(this.email,this.password);

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

    let x: Enseignant = {  mail:"oui@gmail.com",pseudo:"ni", mdp:"na"}
    this.ds.postE( x);
    console.log(x)
  }
}
