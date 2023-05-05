import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { DsService} from "../../service/ds.service";
import { Enseignant } from 'src/app/service/interfaces';
import {UserService} from "../../service/user.service";
import {doc, setDoc} from "firebase/firestore";

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
    let y: Enseignant = {mail: this.email, pseudo: pseudo, mdp: this.password};
    this.newEnseignant(y);
    console.log("la")
    this.auth.register(this.email, this.password);

    this.email = '';
    this.password = '';
  }
    newEnseignant(e: Enseignant) {
    /**
      const docRef = doc(this.us.getFirestore(), "enseignants", e.mail); // on utilise l'email comme ID du document
      setDoc(docRef, e)
        .then(() => {
          console.log("Enseignant enregistré avec succès sur Firestore !");
        })
        .catch((error) => {
          console.error("Erreur lors de l'enregistrement de l'enseignant sur Firestore : ", error);
        });
 */
      this.ds.postE(e)
        .then(enseignant => console.log(enseignant))
        .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
    }
    /**
    wawawa() {

      let x: Enseignant = {  mail:"oui@gmail.com",pseudo:"ni", mdp:"na"}
      this.ds.postE( x);
      console.log(x)
    } **/
  }
