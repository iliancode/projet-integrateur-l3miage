import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { DsService} from "../../service/ds.service";
import { Enseignant } from 'src/app/service/interfaces';
import {doc, setDoc} from "firebase/firestore";
import {UserService} from "../../service/user.service";
import {db} from "../../../environments/test";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email : string = '';
  password : string = '';

  constructor(private auth : AuthService, private ds :DsService, private us : UserService) { }

  ngOnInit(): void {
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

    this.ds.recupUser(this.email, pseudo, this.password);
    //let y: Enseignant = {uid:"zdzed", mail: this.email, pseudo: pseudo, mdp: this.password};
   // this.newEnseignant(y);
    console.log("la")
    console.log(this.email , this.password)
    this.auth.register(this.email, this.password);


    this.email = '';
    this.password = '';
  }

  }
